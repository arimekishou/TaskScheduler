package by.vironit.taskscheduler.dao;

import by.vironit.taskscheduler.entities.User;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void create(User user) throws SQLException;

    List<User> getAll() throws SQLException;

    List<String> getAllEmails() throws SQLException;

    User getById(int id) throws SQLException;

    User getByEmail(String email) throws SQLException;

    User getByRole(String role) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;

}