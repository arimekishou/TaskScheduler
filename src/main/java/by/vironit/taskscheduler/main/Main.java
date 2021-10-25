package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.TaskGroupService;
import by.vironit.taskscheduler.service.UserService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        User user = new User();
        UserService userService = new UserService();
        System.out.println(userService.getAll());

    }

}
