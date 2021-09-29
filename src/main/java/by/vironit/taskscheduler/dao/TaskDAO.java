package by.vironit.taskscheduler.dao;

import by.vironit.taskscheduler.entities.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    void create(Task task) throws SQLException;

    List<Task> getAll() throws SQLException;

    Task getByIdAndTaskGroupId(int id, int taskGroupId) throws SQLException;

    void update(Task task) throws SQLException;

    void delete(Task task) throws SQLException;

}