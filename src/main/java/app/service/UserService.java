package app.service;

import app.DAO.UserDAO;
import app.DAO.Util;
import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends Util implements UserDAO {

    Connection connection = getConnection();

    @Override
    public void create(User user) throws SQLException {

        String sql = "INSERT INTO public.user (id, name, password, email) VALUES (DEFAULT, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create user ERROR");
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT name, password, email FROM public.user";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

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
        } finally {
            if (connection != null) connection.close();
        }
        return userList;
    }

    @Override
    public User getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, name, password, email FROM public.user WHERE id=?";

        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
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
                preparedStatement.close();
            }
            if (connection != null) connection.close();
        }
        return user;
    }

    @Override
    public User getByName(String name) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, name, password, email FROM public.user WHERE name=?";

        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
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
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return user;
    }

    @Override
    public void update(User user) throws SQLException {

        String sql = "UPDATE public.user SET name=?, password=?, email=? WHERE id=DEFAULT";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update user failed");
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public void delete(User user) throws SQLException {

        String sql = "DELETE FROM public.user WHERE id=DEFAULT OR name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(1, user.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete user failed");
        } finally {
            if (connection != null) connection.close();
        }
    }
}
