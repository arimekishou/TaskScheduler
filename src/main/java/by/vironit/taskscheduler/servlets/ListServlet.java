package by.vironit.taskscheduler.servlets;

import by.vironit.taskscheduler.entities.ModelNeedToCorrectThis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelNeedToCorrectThis modelNeedToCorrectThis = ModelNeedToCorrectThis.getInstance();
        List<String> names = modelNeedToCorrectThis.list();
        req.setAttribute("userNames", names);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("appearance/list.jsp");
        requestDispatcher.forward(req, resp);
    }

}
