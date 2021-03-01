package ictgradschool.project.util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;


@WebServlet(name = "createUserServlet", urlPatterns = {"/createUser"})
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


       try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

           //Create new user from input fields on Create new user page.
           User user = new User();
           user.setfName(req.getParameter("firstname"));
           user.setlName(req.getParameter("lastname"));
           user.setUserName(req.getParameter("username"));
           user.setDateOfBirth(req.getParameter("dateofbirth"));
           user.setBio(req.getParameter("selfintroduction"));
           user.setProfileImage(req.getParameter("avatar"));

           //Get a salt and set users salt using base64Encode formula.
           byte[] userSalt = PasswordUtil.getNextSalt();
           user.setSalt(PasswordUtil.base64Encode(userSalt));

           //Get user password convert to char[] and encode
           String unHashedPassword = req.getParameter("confirmed_password");
           char[] unHashedPasswordChar = unHashedPassword.toCharArray();
           byte[] hashedAndSaltedPasswordArray = PasswordUtil.hash(unHashedPasswordChar, userSalt);
           String hashedPasswordString = PasswordUtil.base64Encode(hashedAndSaltedPasswordArray);

           user.setHashCode(hashedPasswordString);

           UserDAO.insertUserGenerateNewUserID(user,connection);
           //redirect to the login page
           req.getRequestDispatcher("/Login.jsp").forward(req,resp);

       }catch (SQLException e){
           System.out.println("Something is wrong with the CreateUserServlet");
           e.printStackTrace();
           resp.setStatus(500);
           throw new ServletException("Database access error");
       }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
