package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.User;
import by.vironit.taskscheduler.service.TaskGroupService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        User user = new User();
        user.setId(173);
        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setUser(user);
        taskGroups.setTitle("54654");
        TaskGroupService taskGroupService = new TaskGroupService();
        taskGroupService.create(taskGroups);

    }

}
