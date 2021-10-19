package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.service.UserService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        String email = "BorisDrozdov@mail.ru";
        String password = "asd";
        UserService userService = new UserService();

        System.out.println(userService.getByRole(email).getRole().equals("admin"));

    }
}
