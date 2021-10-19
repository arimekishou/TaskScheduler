package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.dao.TaskDAO;
import by.vironit.taskscheduler.dao.impl.TaskDaoImpl;
import by.vironit.taskscheduler.entities.Task;

import java.sql.SQLException;
import java.util.List;

public class TaskService implements TaskDAO {

    private TaskDaoImpl taskDaoImpl;

    public TaskService() {
        taskDaoImpl = new TaskDaoImpl();
    }

    @Override
    public void create(Task task) throws SQLException {
        taskDaoImpl.create(task);
    }

    @Override
    public List<Task> getAll() {
        return taskDaoImpl.getAll();
    }

    @Override
    public Task getById(int id) {
        return taskDaoImpl.getById(id);
    }

    @Override
    public void update(Task task) {
        taskDaoImpl.update(task);
    }

    @Override
    public void delete(Task task) {
        taskDaoImpl.delete(task);
    }

}
