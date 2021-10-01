package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.UserDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends Util implements UserDAO {

    private final static String INSERT = "INSERT INTO public.user (name, password, email) VALUES (?, ?, ?)";
    private final static String SELECT = "SELECT name, password, email FROM public.user";
    private final static String GET_BY_ID = "SELECT id, name, password, email FROM public.user WHERE id=?";
    private final static String GET_BY_NAME = "SELECT id, name, password, email FROM public.user WHERE name=?";
    private final static String UPDATE = "UPDATE public.user SET name=?, password=?, email=? WHERE id=?";
    private final static String DELETE = "DELETE FROM public.user WHERE id=? OR email=?";

    @Override
    public void create(User user) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            int i = 0;

            preparedStatement.setString(++i, user.getName());
            preparedStatement.setString(++i, user.getPassword());
            preparedStatement.setString(++i, user.getEmail());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
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
    public List<User> getAll() {

        List<User> userList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT)) {

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

            int i = 0;
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(++i, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }

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

            int i = 0;

            preparedStatement = connection.prepareStatement(GET_BY_NAME);
            preparedStatement.setString(++i, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }

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
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            int i = 0;

            preparedStatement.setString(++i, user.getName());
            preparedStatement.setString(++i, user.getPassword());
            preparedStatement.setString(++i, user.getName());
            preparedStatement.setInt(++i, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update user failed");
        }
    }

    @Override
    public void delete(User user) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            int i = 0;

            preparedStatement.setInt(++i, user.getId());
            preparedStatement.setString(++i, user.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete user failed");
        }
    }

}
