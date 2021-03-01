package ictgradschool.project.util;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "comment", urlPatterns = {"/comment"})
public class CommentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            if (req.getParameter("create") != null) {
                Comment comment = new Comment();
                comment.setArticleId(Integer.parseInt(req.getParameter("article")));
                comment.setText(req.getParameter("create"));
                //Make sure to test that the
                comment.setUsername(req.getParameter("username"));
                comment.setUserId(Integer.parseInt(req.getParameter("user")));


                if (CommentDAO.insertComment(connection, comment)) {
                    comment = CommentDAO.getCommentById(connection, comment.getId());
                    JSONResponse.send(resp, comment);
                }
            } else if (req.getParameter("all") != null) {
                int articleId = Integer.parseInt(req.getParameter("all"));
                List<Comment> commentList = CommentDAO.getAllComments(connection, articleId);

                JSONResponse.send(resp, commentList);

            } else if (req.getParameter("delete") != null) {
                //Gets the comment id of the comment to be deleted
                int commentId = Integer.parseInt(req.getParameter("delete"));
                //If the deletion is successful return the remaining comments
                if (CommentDAO.deleteComment(connection, commentId)) {

                    List<Comment> comments = CommentDAO.getAllComments(connection, Integer.parseInt(req.getParameter("article")));
                    JSONResponse.send(resp, comments);
                }

            }

        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error", e);
        }
    }
}
