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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "UserLoginServlet", urlPatterns = {"/userlogin"})
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            //Parameters are from the Login page.
            String userName = req.getParameter("username");
            String password = req.getParameter("password");

            //User object created based on the username.
            User user = UserDAO.getUser(userName, connection);
            //If username isn't in the database the user will be null
            if (user == null) {
                //message send back to the client
                String usernameFail = "The username is not found please try again";
                req.setAttribute("username", usernameFail);
                //Client sent back to login page to try again
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            //Salt and hashed password reformatted to byte and char array.
            String hashedPasswordValue = user.getHashCode();
            String saltValue = user.getSalt();
            byte[] hashedPassword = PasswordUtil.base64Decode(hashedPasswordValue);
            byte[] salt = PasswordUtil.base64Decode(saltValue);
            char[] charPassword = password.toCharArray();

            //Password is validated.
            if (PasswordUtil.isExpectedPassword(charPassword, salt, hashedPassword)) {
                //All users Articles are accessed to be displayed
                List<Article> articlesList = ArticleDAO.getArticlesByAuthor(user.getId(), connection);
                req.setAttribute("articles", articlesList);
                //Check if Session exists
                HttpSession session = req.getSession(true);
                //Set Session Attribute
                session.setAttribute("username", userName);
                session.setAttribute("userID", user.getId());
                req.setAttribute("user", user);
                //User sent to the user home page.
                //TODO add . before the /UserHomePage.jsp
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserHomePage.jsp");
                dispatcher.forward(req, resp);

            } else {
                //Response to re-enter the password
                String passwordFail = "Password incorrect please try again";
                req.setAttribute("password", passwordFail);
                //User sent back to Login page to try again.
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
                dispatcher.forward(req, resp);
            }

        } catch (SQLException e) {
            System.out.println("Something is wrong with the UserLoginServlet");
            e.printStackTrace();
            resp.setStatus(500);
            throw new ServletException("Database access error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Post method used so that username and password doesnt display in the url.
        doGet(req, resp);
    }
}
