package by.vironit.taskscheduler.controllers.auth;

import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");


        try {
            if (userService.getAllEmails().contains(email) &&
                    userService.getByEmail(email).getPassword().contains(password) &&
                    userService.getByRole(email).getRole().equals("admin")) {
                User user = new User();
                String name = user.getName();
                req.getParameter("name");
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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/*", method = RequestMethod.POST)
    protected void doReg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");

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

    }

}
