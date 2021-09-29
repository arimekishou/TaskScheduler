package app.DAO;

import app.entities.TaskGroups;

import java.sql.SQLException;
import java.util.List;

public interface TaskGroupsDAO {
    void create(TaskGroups taskGroups) throws SQLException;

    List<TaskGroups> getAll() throws SQLException;

    TaskGroups getByIdAndUserId(int id, int user_id) throws SQLException;

    void update(TaskGroups taskGroups) throws SQLException;

    void delete(TaskGroups taskGroups) throws SQLException;
}