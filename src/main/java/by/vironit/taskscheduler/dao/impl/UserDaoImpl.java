package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.UserDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl extends Util implements UserDAO {

    @Override
    public void create(User user) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAll() {
        List<User> users = (List<User>) Util.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public List<String> getAllEmails() {
        List<String> emails = (List<String>) Util.getSessionFactory().openSession().createQuery("select email from User").list();
        return emails;
    }

    @Override
    public User getById(int id) {
        return Util.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        Session session = Util.getSessionFactory().openSession();
        User user = (User) session.createQuery("from User u where u.email = :email ").
                setParameter("email", email).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User getByRole(String role) {
        Session session = Util.getSessionFactory().openSession();
        User user = (User) session.createQuery("from User u where u.email = :email ").
                setParameter("email", role).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User getByPassword(String password) {
        Session session = Util.getSessionFactory().openSession();
        User user = (User) session.createQuery("from User u where u.password = :password ").
                setParameter("password", password).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void update(User user) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        user = session.get(User.class, user.getId());
        session.delete(user);
        tx1.commit();
        session.close();
    }

}
