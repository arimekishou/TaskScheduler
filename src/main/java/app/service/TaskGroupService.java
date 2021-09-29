package app.service;

import app.DAO.TaskGroupsDAO;
import app.DAO.Util;
import app.entities.TaskGroups;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskGroupService extends Util implements TaskGroupsDAO {

    Connection connection = getConnection();

    @Override
    public void create(TaskGroups taskGroups) throws SQLException {

        String sql = "INSERT INTO public.task_groups (id, user_id, title) VALUES (DEFAULT, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, taskGroups.getUserId());
            preparedStatement.setString(2, taskGroups.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create task group ERROR");
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public List<TaskGroups> getAll() throws SQLException {
        List<TaskGroups> taskGroupsList = new ArrayList<>();

        String sql = "SELECT id, user_id, title FROM public.task_groups";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                TaskGroups taskGroups = new TaskGroups();
                taskGroups.setId(resultSet.getInt("id"));
                taskGroups.setUserId(resultSet.getInt("user_id"));
                taskGroups.setTitle(resultSet.getString("title"));

                taskGroupsList.add(taskGroups);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get all from task group failed");
        } finally {
            if (connection != null) connection.close();
        }
        return taskGroupsList;
    }

    @Override
    public TaskGroups getByIdAndUserId(int id, int user_id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, user_id FROM public.task_groups WHERE id=? AND user_id=?";

        TaskGroups taskGroups = new TaskGroups();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            taskGroups.setId(resultSet.getInt("id"));
            taskGroups.setUserId(resultSet.getInt("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get by id and user id failed");
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return taskGroups;
    }

    @Override
    public void update(TaskGroups taskGroups) throws SQLException {

        String sql = "UPDATE public.task_groups SET title=? WHERE id=DEFAULT AND user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, taskGroups.getTitle());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update title failed");
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public void delete(TaskGroups taskGroups) throws SQLException {

        String sql = "DELETE FROM public.task_groups WHERE id=? OR user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, taskGroups.getId());
            preparedStatement.setInt(2, taskGroups.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
        }
    }
}
