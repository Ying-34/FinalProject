package ictgradschool.project.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    //This method will return all of the comments associated with an ArticleId
    public static List<Comment> getAllComments(Connection conn, int articleId) throws SQLException {
        List<Comment> commentsList = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM blogging_comment WHERE articleId = ?")) {
            statement.setInt(1, articleId);
            try (ResultSet res = statement.executeQuery()) {
                //Creates a comment object and adds it to the comment array to be returned
                while (res.next()) {
                    Comment comment = createCommentFromResultSet(res);
                    commentsList.add(comment);
                }
            }
        }
        return commentsList;
    }

    //Deletes a comment, based on a commentId
    public static boolean deleteComment(Connection conn, int commentId) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM blogging_comment WHERE id = ?")) {
            statement.setInt(1, commentId);
            int rowsAffected = statement.executeUpdate();
            //Returns true if the comment is successfully deleted
            return (rowsAffected == 1);
        }
    }

    //Deletes all comments on a specified article - using articleId to identify th article
    public static boolean deleteAllComments(Connection conn, int articleId) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM blogging_comment WHERE articleId = ?")) {
            statement.setInt(1, articleId);

            int rowsAffected = statement.executeUpdate();

            return (rowsAffected >= 0);
        }
    }


    //Inserts a comment into the SQL Database
    public static boolean insertComment(Connection conn, Comment comment) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO blogging_comment (text, username, userId, articleId) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, comment.getText());
            statement.setString(2, comment.getUsername());
            statement.setInt(3, comment.getUserId());
            statement.setInt(4, comment.getArticleId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }
            //Returns the comment's ID from the database and assigns it to the comment
            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                int commentId = keys.getInt(1);
                comment.setId(commentId);

                return true;
            }
        }
    }


    //This will create an article from the result set from a database query
    public static Comment createCommentFromResultSet(ResultSet res) throws SQLException {
        Comment comment = new Comment(
                res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getInt(5),
                res.getInt(6)
        );

        return comment;
    }

    public static Comment getCommentById(Connection conn, int commentId) throws SQLException {
        Comment comment = new Comment();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM blogging_comment WHERE (id = ?)")) {
            statement.setInt(1, commentId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    comment = createCommentFromResultSet(rs);
                }
            }
        }

        return comment;
    }

    public static boolean deleteCommentsByUserId(int userId, Connection conn) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM blogging_comment WHERE userId = ?")) {
            statement.setInt(1, userId);

            int rowsAffected = statement.executeUpdate();

            return (rowsAffected == 1);
        }
    }

    public static boolean updateCommentUsername(Comment comment, Connection conn) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(
                "UPDATE blogging_comment SET username = ? WHERE id = ?")) {

            statement.setString(1, comment.getUsername());
            statement.setInt(2, comment.getId());

            int rowsAffected = statement.executeUpdate();

            return (rowsAffected == 1);
        }
    }


}
