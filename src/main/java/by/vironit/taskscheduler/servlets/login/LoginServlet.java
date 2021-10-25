package by.vironit.taskscheduler.servlets.login;

import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class LoginServlet {

    private final UserService userService;

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            if (userService.getAllEmails().contains(email) &&
                    userService.getByEmail(email).getPassword().contains(password) &&
                    userService.getByRole(email).getRole().equals("admin")) {
                req.getRequestDispatcher("/view/adminPage.jsp").forward(req, resp);
            } else if (userService.getAllEmails().contains(email) &&
                    userService.getByEmail(email).getPassword().contains(password) &&
                    userService.getByRole(email).getRole().equals("user")) {
                req.getRequestDispatcher("/view/taskPage.jsp").forward(req, resp);

            } else req.getRequestDispatcher("/view/loginError.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
