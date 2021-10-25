package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.dao.UserDAO;
import by.vironit.taskscheduler.dao.impl.UserDaoImpl;
import by.vironit.taskscheduler.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService implements UserDAO {

    private UserDaoImpl userDaoImpl;

    public UserService() {
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    @Transactional
    public void create(User user) {
        userDaoImpl.create(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDaoImpl.getAll();
    }

    @Override
    @Transactional
    public List<String> getAllEmails() throws SQLException {
        return userDaoImpl.getAllEmails();
    }

    @Override
    @Transactional
    public User getById(int id) {
        User user = userDaoImpl.getById(id);
        return user;
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        User user = userDaoImpl.getByEmail(email);
        return user;
    }

    @Override
    @Transactional
    public User getByRole(String role) throws SQLException {
        User user = userDaoImpl.getByRole(role);
        return user;
    }

    @Override
    @Transactional
    public User getByPassword(String password) {
        User user = userDaoImpl.getByPassword(password);
        return user;
    }

    @Override
    @Transactional
    public void update(User user) {
        userDaoImpl.update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDaoImpl.delete(user);
    }

}