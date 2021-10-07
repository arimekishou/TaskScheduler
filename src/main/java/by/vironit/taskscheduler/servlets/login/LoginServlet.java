package by.vironit.taskscheduler.servlets.login;

import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserService userService = new UserService();

        try {
            if (userService.getAllEmails().contains(email) && userService.getByEmail(email).getPassword().contains(password)) {
                req.getRequestDispatcher("/WEB-INF/view/taskPage.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/loginError.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}


