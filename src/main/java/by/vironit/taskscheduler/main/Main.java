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
        user.setEmail("boris1123@mail.ru");
        user.setRole("admin");

        userService.create(user);

    }

}
