package ictgradschool.project.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "deleteUserServlet", urlPatterns = {"/deleteuser"})
public class DeleteUserServlet extends HttpServlet {

    User user;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            //Get current user on load using the edit button on user details page.
            if (req.getParameter("userdetails") != null) {
                String userName = req.getParameter("user");
                user = UserDAO.getUser(userName, connection);
                req.setAttribute("user", user);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserDetails.jsp");
                dispatcher.forward(req, resp);
            }
            //Get current user
            if (req.getParameter("getuser") != null) {
                String userName = req.getParameter("getuser");
                user = UserDAO.getUser(userName, connection);
                req.setAttribute("user", user);
                JSONResponse.send(resp, user);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserDetails.jsp");
                dispatcher.forward(req, resp);
            }

            if (req.getAttribute("edit") != null) {

                List<User> userList = UserDAO.getAllUsers(connection);
                List<String> userNames = createUserNameList(userList);
                String newUsername = (String) req.getAttribute("username");

                if (userNameTaken(newUsername, userNames)&&!(newUsername.equals(user.getUserName()))) {

                    String userNameTaken = "Sorry that username is taken";
                    req.setAttribute("userMessage", userNameTaken);
                    //this idea is that this will be an alert message
                    req.setAttribute("user", user);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserDetails.jsp");
                    dispatcher.forward(req, resp);
                } else {

                    //Retrieve the session so that the userId and original username can be accessed
                    HttpSession session = req.getSession(true);
                    String priorUsername = (String) session.getAttribute("username");
                    //Set-up the user changes by getting the attributes assigned in the FileUpload servlet
                    user = UserDAO.getUser(priorUsername, connection);
                    user.setUserName(newUsername);
                    user.setBio((String) req.getAttribute("bio"));
                    user.setfName((String) req.getAttribute("firstname"));
                    user.setlName((String) req.getAttribute("lastname"));
                    user.setProfileImage((String) req.getAttribute("image"));
                    user.setId((int) session.getAttribute("userID"));
                    boolean userUpdated = UserDAO.updateExistingUser(user, connection);

                    if (userUpdated) {

                        session.setAttribute("username", newUsername);
                        List<Article> articles = ArticleDAO.getArticlesByAuthor(user.getId(), connection);

                        for (Article article : articles) {
                            article.setUsername(newUsername);
                            ArticleDAO.updateArticleUsername(article, connection);
                            List<Comment> comments = CommentDAO.getAllComments(connection, article.getId());

                            for (Comment comment : comments) {
                                if (comment.getUserId() == article.getAuthorId()) {
                                    comment.setUsername(newUsername);
                                    CommentDAO.updateCommentUsername(comment, connection);
                                }
                            }
                        }

                        req.setAttribute("user", user);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserHomePage.jsp");
                        dispatcher.forward(req, resp);
                    }
                }

            }

            if (req.getParameter("delete") != null) {
                int userID = user.getId();
                List<Article> article = ArticleDAO.getArticlesByAuthor(userID, connection);

                for (Article value : article) {
                    int articleID = value.getId();
                    ArticleDAO.deleteArticle(articleID, connection);
                }
                CommentDAO.deleteCommentsByUserId(userID, connection);
                UserDAO.deleteUser(userID, connection);
                HttpSession session = req.getSession(true);
                session.invalidate();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
                dispatcher.forward(req, resp);

            }


        } catch (SQLException e) {
            System.out.println("Something is wrong with the UserServlet");
            e.printStackTrace();
            resp.setStatus(500);
            throw new ServletException("Database access error");
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public List<String> createUserNameList(List<User> users) {
        List<String> userNameList = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            String username = users.get(i).getUserName();
            userNameList.add(i, username);
        }

        return userNameList;
    }

    public boolean userNameTaken(String newUserName, List<String> userNames) {

        for (String userName : userNames) {
            if (newUserName.equals(userName)) {
                return true;
            }
        }
        return false;
    }


}
