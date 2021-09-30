package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.dao.impl.TaskDaoImpl;
import by.vironit.taskscheduler.dao.impl.TaskGroupDaoImpl;
import by.vironit.taskscheduler.dao.impl.UserDaoImpl;
import by.vironit.taskscheduler.entities.User;

public class Main {

    public static void main(String[] args) {

        UserDaoImpl userDaoImpl = new UserDaoImpl();
        TaskGroupDaoImpl taskGroupDaoImpl = new TaskGroupDaoImpl();
        TaskDaoImpl taskDaoImpl = new TaskDaoImpl();

        User user = new User();
        user.setId(74);
        user.setName("admin");
        user.setPassword("admin");
        user.setEmail("admin");
        /*user.setEmail("boris2@mail.ru");*/

        userDaoImpl.update(user);


       /* TaskGroups taskGroups = new TaskGroups();
        taskGroups.setUserId(user.getId());
        taskGroups.setTitle("группа 3");

        taskGroupDaoImpl.create(taskGroups);

        Task task = new Task();
        task.setTaskGroupId(taskGroups.getId());
        task.setTitle("Создать");
        task.setTaskDescription("description");
        task.setStatus("Done");
        task.setStartDate(Date.valueOf(LocalDate.now()));
        task.setEndDate(Date.valueOf("2021-10-12"));

        taskDaoImpl.create(task);*/

    }

}
