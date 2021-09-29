package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.UserDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends Util implements UserDAO {

    private final static String insert = "INSERT INTO public.user (name, password, email) VALUES (?, ?, ?)";
    private final static String select = "SELECT name, password, email FROM public.user";
    private final static String getById = "SELECT id, name, password, email FROM public.user WHERE id=?";
    private final static String getByName = "SELECT id, name, password, email FROM public.user WHERE name=?";
    private final static String update = "UPDATE public.user SET name=?, password=?, email=? WHERE id=DEFAULT";
    private final static String delete = "DELETE FROM public.user WHERE id=? AND email=?";

    @Override
    public void create(User user) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create user ERROR.");
        }
    }

    @Override
    public List<User> getAll() {

        List<User> userList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(select)) {

            while (resultSet.next()) {

                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));

                userList.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get all from user failed");
        }
        return userList;
    }

    @Override
    public User getById(int id) {

        PreparedStatement preparedStatement = null;
        User user = new User();

        try (Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get by id from user failed");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return user;
        }
    }

    @Override
    public User getByName(String name) {

        PreparedStatement preparedStatement = null;
        User user = new User();

        try (Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement(getByName);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Get by name from user failed");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    @Override
    public void update(User user) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update user failed");
        }
    }

    @Override
    public void delete(User user) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete user failed");
        }
    }

}
