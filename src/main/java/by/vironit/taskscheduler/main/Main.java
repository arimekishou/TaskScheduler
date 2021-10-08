package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.UserService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserService userService = new UserService();
        User user = new User();
        user.setName("boris");
        user.setPassword("boris");
        user.setEmail("b123123123o23@mail.ru");
        user.setRole("notone");

        userService.create(user);

    }

}
