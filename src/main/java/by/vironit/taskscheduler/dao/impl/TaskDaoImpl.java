package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.TaskDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDaoImpl extends Util implements TaskDAO {

    @Override
    public void create(Task task) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Task> getAll() {
        List<Task> taskList = (List<Task>) Util.getSessionFactory().openSession().createQuery("From Task").list();
        return taskList;
    }

    @Override
    public Task getById(int id) {
        return Util.getSessionFactory().openSession().get(Task.class, id);
    }

    @Override
    public void update(Task task) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Task task) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        task = session.get(Task.class, task.getId());
        session.delete(task);
        tx1.commit();
        session.close();
    }

}
