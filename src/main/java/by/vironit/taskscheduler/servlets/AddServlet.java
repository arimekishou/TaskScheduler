package by.vironit.taskscheduler.servlets;

import by.vironit.taskscheduler.entities.ModelNeedToCorrectThis;
import by.vironit.taskscheduler.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("appearance/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(name, password, email);
        ModelNeedToCorrectThis modelNeedToCorrectThis = ModelNeedToCorrectThis.getInstance();
        modelNeedToCorrectThis.add(user);

        req.setAttribute("userName", name);
        doGet(req, resp);
    }

}
