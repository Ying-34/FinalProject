package ictgradschool.project.articleservlet;


import ictgradschool.project.util.Article;
import ictgradschool.project.util.ArticleDAO;
import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.JSONResponse;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@WebServlet(name = "article", urlPatterns = {"/article"})
public class ArticlesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            //Deletes an a
            if (req.getParameter("delete") != null) {

                int articleId = Integer.parseInt(req.getParameter("articleId"));

                if (ArticleDAO.deleteArticle(articleId, connection)) {
                    req.getRequestDispatcher("/UserHomePage.jsp").forward(req, resp);
                }

            } else if (req.getParameter("update") != null) {
                //If the value is to update an article, use the articleId to return the article from SQL.
                int articleId = Integer.parseInt(req.getParameter("articleId"));
                Article article = ArticleDAO.getArticle(connection, articleId);

                //Get all variables from the JSP file that are changeable and set them to the article
                article.setText(req.getParameter("text"));
                article.setTitle(req.getParameter("title"));


                //If the update was successful return a list of articles by that author, including the updated article
                if (ArticleDAO.updateArticle(article, connection)) {
                    List<Article> articleList = ArticleDAO.getArticlesByAuthor(article.getAuthorId(), connection);
                    req.setAttribute("articles", articleList);
                    req.getRequestDispatcher("/UserHomePage.jsp").forward(req, resp);
                }


            } else if (req.getAttribute("add") != null) {
                //This adds and article to the database. FileUpload Servlet forwards here
                Article article = new Article();
                article.setText((String) req.getAttribute("text"));
                article.setTitle((String) req.getAttribute("title"));
                article.setImageFile((String) req.getAttribute("image"));

                //This returns the session details i.e. the username and userid and sets them to the article
                HttpSession session = req.getSession(true);

                article.setAuthorId((Integer) session.getAttribute("userID"));
                article.setUsername((String) session.getAttribute("username"));
                //If the article is successfully inserted then the database will return all of the authors articles (inclding the new one) and sends them to the user's home page
                if (ArticleDAO.insertArticle(article, connection)) {
                    List<Article> articleList = ArticleDAO.getArticlesByAuthor(article.getId(), connection);
                    req.setAttribute("articles", articleList);
                    req.getRequestDispatcher("./UserHomePage.jsp").forward(req, resp);
                }
            } else if ((Integer.parseInt(req.getParameter("one")) > 0)) {
                //Loads a single article based on an articleId
                int articleId = Integer.parseInt(req.getParameter("one"));
                Article article = ArticleDAO.getArticle(connection, articleId);
                JSONResponse.send(resp, article);
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        } catch (NullPointerException e) {
            throw new ServletException("File not found", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Forwards the fileupload servlet doPost to the articles servlet doGet method
        doGet(req, resp);
    }


}
