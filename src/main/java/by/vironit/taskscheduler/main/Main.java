package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.dao.impl.TaskDaoImpl;
import by.vironit.taskscheduler.dao.impl.TaskGroupDaoImpl;
import by.vironit.taskscheduler.dao.impl.UserDaoImpl;
import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.User;

public class Main {
    public static void main(String[] args) {

        UserDaoImpl userDaoImpl = new UserDaoImpl();
        TaskGroupDaoImpl taskGroupDaoImpl = new TaskGroupDaoImpl();
        TaskDaoImpl taskDaoImpl = new TaskDaoImpl();

        User user = new User();
        user.setName("Boris");
        user.setPassword("123");
        user.setEmail("boris@mail.ru");

        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setTitle("First task!");
        taskGroups.setUserId(user.getId());

        Task task = new Task();
        task.setTaskGroupId(taskGroups.getId());
        task.setTitle("test");
        task.setTaskDescription("test description");
        task.setStatus("Done");

        userDaoImpl.create(user);
        taskGroupDaoImpl.create(taskGroups);
        taskDaoImpl.create(task);
    }
}
