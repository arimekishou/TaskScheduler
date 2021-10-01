package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.UserService;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();

        System.out.println(userService.getAll());

    }

}
