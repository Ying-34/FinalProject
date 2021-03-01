package ictgradschool.project.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    //This allows us to return every article from the database
    public static List<Article> getAllArticles(Connection connection) throws SQLException {

        List<Article> articles = new ArrayList<>();
        try(Statement statement = connection.createStatement()){

            try (ResultSet res = statement.executeQuery( "SELECT * FROM bloggingDB_article ORDER BY id DESC")){

                while(res.next()){
                    Article article = createArticleFromResultSet(res);
                    //This sets the blurb of the article so that it's already set when returned
                    article.setBlurb();
                    articles.add(article);
                }
            }
        }
        return articles;
    }


    //This method takes an article id as an input and returns the article from the database
    public static Article getArticle(Connection connection, int id) throws SQLException{
        Article article;

        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM bloggingDB_article WHERE id = ?")) {
            statement.setInt(1, id);

            try(ResultSet res = statement.executeQuery()){
                int articleId = 0;
                while(res.next()){
                    if(res.getInt(1) == id){
                        articleId = res.getInt(1);
                        break;
                    }
                }
                if(articleId <= 0){
                    return null;
                }
                article = createArticleFromResultSet(res);
            }
        }
        return article;
    }


    //This returns a list of articles written by a specific author
    public static List<Article> getArticlesByAuthor(int userId, Connection conn) throws SQLException {
        List<Article> articles = new ArrayList<>();

        try(PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM bloggingDB_article WHERE authorId = ?")){

            statement.setInt(1, userId);

            try(ResultSet res = statement.executeQuery()){

                while(res.next()){
                    Article article = createArticleFromResultSet(res);

                    articles.add(article);
                }
            }
        }
        return articles;
    }


    //Gets a list of provided articles and sorts them based on a specified search parameter and order then returns the article list
    public static List<Article> getArticlesByIdList (Connection connection, String articleIds, String direction, String order) throws SQLException {
        List<Article> articles = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            String query = "SELECT * FROM bloggingDB_article WHERE id IN ("+articleIds+") ORDER  BY "+order+" "+direction;

            try(ResultSet res = statement.executeQuery(query)){

                while(res.next()){
                    Article article = createArticleFromResultSet(res);
                    articles.add(article);
                }
                if(res.getRow()==0){
                    return null;
                }
            }
        }
        return articles;
    }


    //This will create an article from the result set from a database query
    private static Article createArticleFromResultSet(ResultSet res) throws SQLException{
        Article article = new Article(
                res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getInt(7),
                res.getString(8)
        );
        return article;
    }

    //This will update an article, with an article as the input. Given the article id, the SQL table will be updated with the text, title, imageFile, updateEntryDate, updateEntryTime
    //When the update is sent, it must send through the aforementioned variables - otherwise it may result in variables that weren't intended to be over-ridden to be replaced with null.
    public static boolean updateArticle(Article article, Connection conn) throws SQLException {

        try(PreparedStatement statement = conn.prepareStatement(
                "UPDATE bloggingDB_article SET text = ?, title = ? WHERE id = ?")){
            statement.setString(1,article.getText());
            statement.setString(2,article.getTitle());
            statement.setInt(3,article.getId());

            int rowsAffected = statement.executeUpdate();

            return (rowsAffected == 1);
        }
    }

    public static boolean updateArticleUsername(Article article, Connection conn) throws SQLException {
        try(PreparedStatement statement = conn.prepareStatement(
                "UPDATE bloggingDB_article SET username = ? WHERE id = ?")){

            statement.setString(1,article.getUsername());
            statement.setInt(2,article.getId());

            int rowsAffected = statement.executeUpdate();

            return (rowsAffected == 1);
        }
    }


    //This method will delete an article with a given articleId
    public static boolean deleteArticle(int articleId, Connection conn) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM bloggingDB_article WHERE id = ?")) {
            statement.setInt(1, articleId);

            //Deletes all comments associated with a specified article, before deleting the article (to prevent SQL exception)
            CommentDAO.deleteAllComments(conn, articleId);

            int rowsAffected = statement.executeUpdate();
            return (rowsAffected == 1);
        }
    }


    //This method will create an article and insert it into the database. The id will be autogenerated by the database and returned as a key

    public static boolean insertArticle(Article article, Connection conn) throws SQLException {
        try( PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO bloggingDB_article (text, title, imageFile, authorId, username) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, article.getText());
            statement.setString(2, article.getTitle());
            statement.setString(3, article.getImageFile());
            statement.setInt(4, article.getAuthorId());
            statement.setString(5, article.getUsername());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                int articleId = keys.getInt(1);
                article.setId(articleId);
                article.setBlurb();
                return true;
            }
        }
    }


    //This takes a query as a string and compares it with either saved titles, username, or dateOfEntry (does not compare time) and returns matching articles
    public static List<Article> getArticleByQuery(Connection conn, String query) throws SQLException {
        List<Article> articles = new ArrayList<>();

        try(PreparedStatement statement = conn.prepareStatement(
//                SELECT * FROM `bloggingDB_article` ORDER BY `bloggingDB_article`.`username` ASC
                    "(SELECT * FROM `bloggingDB_article` WHERE (title LIKE ?) OR (username LIKE ?) OR (dateOfEntry LIKE ?)) ")){
            statement.setString(1, "%"+query+"%");
            statement.setString(2, "%"+query+"%");
            statement.setString(3, "%"+query+"%");

            try(ResultSet res = statement.executeQuery()){

                while(res.next()){
                    Article article = createArticleFromResultSet(res);
                    articles.add(article);
                }
                if(res.getRow()==0){
                    return null;
                }
            }
        }
        return articles;
    }
}
