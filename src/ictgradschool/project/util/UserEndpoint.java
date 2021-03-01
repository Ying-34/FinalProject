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

@WebServlet(name = "UserEndpoint", urlPatterns = {"/userendpoint"})
public class UserEndpoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){
            //Creates a list of user objects to send back as a Json Reponse.
            List<User> userList = UserDAO.getAllUsers(connection);
            JSONResponse.send(resp,userList);

        } catch (SQLException e) {
            System.out.println("Something wrong with UserEndPoint Servlet");
            e.printStackTrace();
            resp.setStatus(500);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
