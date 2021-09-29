package app.DAO;

import app.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void create(User user) throws SQLException;

    List<User> getAll() throws SQLException;

    User getById(int id) throws SQLException;

    User getByName(String name) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;
}