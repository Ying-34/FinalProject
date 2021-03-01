package ictgradschool.project.util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet (name = "ArticleEndpoint", urlPatterns = {"/articleendpoint"})
public class ArticleEndpoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){

            if(req.getParameter("query") != null){
                //Takes a query getter and returns any matching articles
                String query = req.getParameter("query");
                List<Article> articles = ArticleDAO.getArticleByQuery(connection , query);
                JSONResponse.send(resp,articles);

            } else if(req.getParameter("all") != null){
                //This returns a list of all articles in the database and returns a JSONResponse
                List<Article> articleList = ArticleDAO.getAllArticles(connection);
                JSONResponse.send(resp,articleList);

            } else if(req.getParameter("author") != null){
                //This gets all articles by a author and returns a JSONResponse
                int authorId = Integer.parseInt(req.getParameter("author"));
                List<Article> articles = ArticleDAO.getArticlesByAuthor(authorId,connection);
                JSONResponse.send(resp, articles);
            } else if(req.getParameter("sort") != null) {
                //This returns a sorted article list, based on a list of articleID inputs
                String authorString = req.getParameter("sort");
                String sortDirection  = req.getParameter("direction");
                String order = req.getParameter("order");

                List<Article> articles = ArticleDAO.getArticlesByIdList(connection, authorString,sortDirection,order);
                JSONResponse.send(resp, articles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
