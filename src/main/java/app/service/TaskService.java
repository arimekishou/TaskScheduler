package app.service;

import app.DAO.TaskDAO;
import app.DAO.Util;
import app.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService extends Util implements TaskDAO {

    Connection connection = getConnection();

    @Override
    public void create(Task task) throws SQLException {

        String sql = "INSERT INTO public.tasks (task_group_id, id, title, \"taskDescription\", \"startDate\", " +
                "\"endDate\", status) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, task.getId());
            preparedStatement.setString(2, task.getTitle());
            preparedStatement.setString(3, task.getTaskDescription());
            preparedStatement.setDate(4, task.getStartDate());
            preparedStatement.setDate(5, task.getEndDate());
            preparedStatement.setString(6, task.getStatus());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create task failed");
        } finally {
            if (connection != null) connection.close();
        }

    }

    @Override
    public List<Task> getAll() throws SQLException {
        List<Task> tasksList = new ArrayList<>();

        String sql = "SELECT task_group_id, id, title, \"taskDescription\", \"startDate\"," +
                "\"endDate\", status FROM public.tasks";
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskGroupId(resultSet.getInt("task_group_id"));
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setTaskDescription(resultSet.getString("taskDescription"));
                task.setStartDate(resultSet.getDate("startDate"));
                task.setEndDate(resultSet.getDate("endDate"));
                task.setStatus(resultSet.getString("status"));

                tasksList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get all from tasks failed");
        } finally {
            if (connection != null) connection.close();
        }
        return tasksList;
    }

    @Override
    public Task getByIdAndTaskGroupId(int id, int taskGroupId) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, task_group_id FROM public.tasks WHERE id=? AND task_group_id=?";

        Task task = new Task();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, taskGroupId);

            ResultSet resultSet = preparedStatement.executeQuery();

            task.setId(resultSet.getInt("id"));
            task.setTaskGroupId(resultSet.getInt("task_group_id"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed get by id and task group id in task");
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return task;
    }

    @Override
    public void update(Task task) throws SQLException {
        String sql = "UPDATE public.tasks SET title=?, \"taskDescription\"=?, \"startDate\"=?, \"endDate\"=?, status=?" +
                "WHERE id=DEFAULT AND task_group_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setDate(3, task.getStartDate());
            preparedStatement.setDate(4, task.getEndDate());
            preparedStatement.setString(5, task.getStatus());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update task failed");
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public void delete(Task task) throws SQLException {
        String sql = "DELETE FROM public.tasks WHERE id=? OR task_group_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, task.getId());
            preparedStatement.setInt(2, task.getTaskGroupId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
        }
    }
}
