package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.TaskGroupsDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.TaskGroups;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskGroupDaoImpl extends Util implements TaskGroupsDAO {

    private final static String insert = "INSERT INTO public.task_groups (title) VALUES (?)";
    private final static String select = "SELECT id, user_id, title FROM public.task_groups";
    private final static String getByIdAndUserId = "SELECT id, user_id FROM public.task_groups WHERE id=? AND user_id=?";
    private final static String update = "UPDATE public.task_groups SET title=? WHERE id=? AND user_id=?";
    private final static String delete = "DELETE FROM public.task_groups WHERE id=? OR user_id=?";

    @Override
    public void create(TaskGroups taskGroups) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            preparedStatement.setString(1, taskGroups.getTitle());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create task group ERROR");
        }
    }

    @Override
    public List<TaskGroups> getAll() {

        List<TaskGroups> taskGroupsList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(select);

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
        }
        return taskGroupsList;
    }

    @Override
    public TaskGroups getByIdAndUserId(int id, int user_id) {

        PreparedStatement preparedStatement = null;
        TaskGroups taskGroups = new TaskGroups();

        try (Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement(getByIdAndUserId);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            taskGroups.setId(resultSet.getInt("id"));
            taskGroups.setUserId(resultSet.getInt("user_id"));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get by id and user id failed");
        }
        return taskGroups;
    }

    @Override
    public void update(TaskGroups taskGroups) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, taskGroups.getTitle());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update title failed");
        }
    }

    @Override
    public void delete(TaskGroups taskGroups) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, taskGroups.getId());
            preparedStatement.setInt(2, taskGroups.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete task group");
        }
    }

}
