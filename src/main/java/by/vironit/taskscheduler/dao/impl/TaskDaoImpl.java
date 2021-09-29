package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.TaskDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl extends Util implements TaskDAO {

    private final static String insert = "INSERT INTO public.tasks (task_group_id, title, task_Description, " +
            "start_Date, end_Date, status) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    private final static String select = "SELECT task_group_id, id, title, task_Description, start_Date," +
            "end_Date, status FROM public.tasks";
    private final static String update = "UPDATE public.tasks SET title=?, task_Description=?, start_Date=?, " +
            "end_Date=?, status=? WHERE id=? AND task_group_id=?";
    private final static String delete = "DELETE FROM public.tasks WHERE id=? OR task_group_id=?";
    private final static String getByIdAndTaskGroupId = "SELECT id, task_group_id FROM public.tasks WHERE id=?" +
            " AND task_group_id=?";

    @Override
    public void create(Task task) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setDate(3, task.getStartDate());
            preparedStatement.setDate(4, task.getEndDate());
            preparedStatement.setString(5, task.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create task failed");
        }
    }

    @Override
    public List<Task> getAll() {

        List<Task> tasksList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(select);

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
        }
        return tasksList;
    }

    @Override
    public Task getByIdAndTaskGroupId(int id, int taskGroupId) throws SQLException {

        PreparedStatement preparedStatement = null;

        Task task = new Task();

        try (Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement(getByIdAndTaskGroupId);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, taskGroupId);

            ResultSet resultSet = preparedStatement.executeQuery();

            task.setId(resultSet.getInt("id"));
            task.setTaskGroupId(resultSet.getInt("task_group_id"));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed get by id and task group id in task");
        }
        return task;
    }

    @Override
    public void update(Task task) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setDate(3, task.getStartDate());
            preparedStatement.setDate(4, task.getEndDate());
            preparedStatement.setString(5, task.getStatus());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update task failed");
        }
    }

    @Override
    public void delete(Task task) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, task.getId());
            preparedStatement.setInt(2, task.getTaskGroupId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
