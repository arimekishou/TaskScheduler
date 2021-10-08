package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.dao.UserDAO;
import by.vironit.taskscheduler.dao.impl.UserDaoImpl;
import by.vironit.taskscheduler.entities.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements UserDAO {

    private UserDaoImpl userDaoImpl;

    public UserService() {
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    public void create(User user) {
        userDaoImpl.create(user);
    }

    @Override
    public List<User> getAll() {
        return userDaoImpl.getAll();
    }

    @Override
    public List<String> getAllEmails() throws SQLException {
        return userDaoImpl.getAllEmails();
    }

    @Override
    public User getById(int id) {
        User user = userDaoImpl.getById(id);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDaoImpl.getByEmail(email);
        return user;
    }

    @Override
    public User getByRole(String role) throws SQLException {
        User user = userDaoImpl.getByRole(role);
        return user;
    }

    @Override
    public void update(User user) {
        userDaoImpl.update(user);
    }

    @Override
    public void delete(User user) {
        userDaoImpl.delete(user);
    }

}