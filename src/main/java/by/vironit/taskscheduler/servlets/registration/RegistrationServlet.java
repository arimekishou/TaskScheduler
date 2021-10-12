package by.vironit.taskscheduler.servlets.registration;

import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");

        UserService userService = new UserService();

        try {
            if (userService.getAllEmails().contains(email)) {
                req.getRequestDispatcher("/view/registrationError.jsp").forward(req, resp);
            } else {
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(role);
                userService.create(user);
                req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("userName", name);
        req.setAttribute("password", password);
        req.setAttribute("email", email);
        req.setAttribute("role", role);
        doGet(req, resp);

    }

}

