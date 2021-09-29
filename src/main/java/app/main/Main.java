package app.main;

import app.entities.Task;
import app.entities.TaskGroups;
import app.entities.User;
import app.service.TaskGroupService;
import app.service.TaskService;
import app.service.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        TaskGroupService taskGroupService = new TaskGroupService();
        TaskService taskService = new TaskService();

        User user = new User();
        user.setId(1);
        user.setName("Boris3");
        user.setPassword("1233");
        user.setEmail("boris3@mail.ru");

        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setId(1);
        taskGroups.setTitle("First task!");
        taskGroups.setUserId(user.getId());

        Task task = new Task();
        task.setId(1);
        task.setTaskGroupId(taskGroups.getId());
        task.setTitle("test");
        task.setTaskDescription("test description");
        task.setStatus("В работе!");
        task.setStatus("Выполнено");

        try {
            userService.create(user);
            taskGroupService.create(taskGroups);
            taskService.create(task);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create user from main method failed");
        }
    }
}
