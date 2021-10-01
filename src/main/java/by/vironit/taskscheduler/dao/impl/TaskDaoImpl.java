package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.TaskDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl extends Util implements TaskDAO {

    private final static String INSERT = "INSERT INTO public.tasks (task_group_id,  title, \"task_Description\", " +
            "\"start_Date\", \"end_Date\", status) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SELECT = "SELECT task_group_id, id, title, \"task_Description\", \"start_Date\"," +
            "\"end_Date\", status FROM public.tasks";
    private final static String UPDATE = "UPDATE public.tasks SET title=?, \"task_Description\"=?, \"start_Date\"=?, " +
            "\"end_Date\"=?, status=? WHERE id=? AND task_group_id=?";
    private final static String DELETE = "DELETE FROM public.tasks WHERE id=? OR task_group_id=?";
    private final static String GET_BY_ID_AND_TASK_GROUP_ID = "SELECT id, task_group_id, task_group_id, id, title," +
            " \"task_Description\", \"start_Date\", \"end_Date\", status FROM public.tasks WHERE id=? AND task_group_id=?";

    @Override
    public void create(Task task) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            int i = 0;

            preparedStatement.setInt(++i, task.getTaskGroupId());
            preparedStatement.setString(++i, task.getTitle());
            preparedStatement.setString(++i, task.getTaskDescription());
            preparedStatement.setDate(++i, task.getStartDate());
            preparedStatement.setDate(++i, task.getEndDate());
            preparedStatement.setString(++i, task.getStatus());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create user ERROR.");
        }
    }

    @Override
    public List<Task> getAll() {

        List<Task> tasksList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskGroupId(resultSet.getInt("task_group_id"));
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setTaskDescription(resultSet.getString("task_Description"));
                task.setStartDate(resultSet.getDate("start_Date"));
                task.setEndDate(resultSet.getDate("end_Date"));
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
    public Task getByIdAndTaskGroupId(int id, int taskGroupId) {

        Task task = new Task();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_AND_TASK_GROUP_ID)) {


            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, taskGroupId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                task.setId(resultSet.getInt("id"));
                task.setTaskGroupId(resultSet.getInt("task_group_id"));
                task.setTitle(resultSet.getString("title"));
                task.setTaskDescription(resultSet.getString("task_Description"));
                task.setStartDate(resultSet.getDate("start_Date"));
                task.setEndDate(resultSet.getDate("end_Date"));
                task.setStatus(resultSet.getString("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed get by id and task group id in task");
        }
        return task;
    }

    @Override
    public void update(Task task) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, task.getId());
            preparedStatement.setInt(2, task.getTaskGroupId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
