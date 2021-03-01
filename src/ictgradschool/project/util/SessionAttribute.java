package ictgradschool.project.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "session-attribute", urlPatterns = {"/session-attribute"})
public class SessionAttribute extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //This is linked to the logout pages
        if (req.getParameter("logout") != null) {
            //Retrieve an existing Session or create a new one if needed
            HttpSession session = req.getSession(true);
            //Delete Session
            session.invalidate();
            //Dispatch back to log in page
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
