package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.dao.TaskGroupsDAO;
import by.vironit.taskscheduler.dao.impl.TaskGroupDaoImpl;
import by.vironit.taskscheduler.entities.TaskGroups;

import java.sql.SQLException;
import java.util.List;

public class TaskGroupService implements TaskGroupsDAO {

    private TaskGroupDaoImpl taskGroupImpl;

    public TaskGroupService() {
        taskGroupImpl = new TaskGroupDaoImpl();
    }

    @Override
    public void create(TaskGroups taskGroups) throws SQLException {
        taskGroupImpl.create(taskGroups);
    }

    @Override
    public List<TaskGroups> getAll() throws SQLException {
        return taskGroupImpl.getAll();
    }

    @Override
    public TaskGroups getByIdAndUserId(int id, int user_id) {
        TaskGroups taskGroups = taskGroupImpl.getByIdAndUserId(id, user_id);
        return taskGroups;
    }

    @Override
    public void update(TaskGroups taskGroups) {
        taskGroupImpl.update(taskGroups);
    }

    @Override
    public void delete(TaskGroups taskGroups) {
        taskGroupImpl.delete(taskGroups);
    }

}
