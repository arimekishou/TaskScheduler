package by.vironit.taskscheduler.main;

import by.vironit.taskscheduler.dao.impl.TaskDaoImpl;
import by.vironit.taskscheduler.dao.impl.TaskGroupDaoImpl;
import by.vironit.taskscheduler.dao.impl.UserDaoImpl;
import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;

public class Main {

    public static void main(String[] args) {

        UserDaoImpl userDaoImpl = new UserDaoImpl();
        TaskGroupDaoImpl taskGroupDaoImpl = new TaskGroupDaoImpl();
        TaskDaoImpl taskDaoImpl = new TaskDaoImpl();

        System.out.println(taskDaoImpl.getByIdAndTaskGroupId(7,73));

    }

}
