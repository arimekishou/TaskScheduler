package by.vironit.taskscheduler.dao.impl;

import by.vironit.taskscheduler.dao.TaskGroupsDAO;
import by.vironit.taskscheduler.dao.Util;
import by.vironit.taskscheduler.entities.TaskGroups;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskGroupDaoImpl extends Util implements TaskGroupsDAO {

    @Override
    public void create(TaskGroups taskGroups) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(taskGroups);
        tx1.commit();
        session.close();
    }

    @Override
    public List<TaskGroups> getAll() {
        List<TaskGroups> taskGroupsList = (List<TaskGroups>) Util.getSessionFactory().openSession().createQuery("from TaskGroups").list();
        return taskGroupsList;
    }

    @Override
    public TaskGroups getById(int id) {
        return Util.getSessionFactory().openSession().get(TaskGroups.class, id);
    }

    @Override
    public void update(TaskGroups taskGroups) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(taskGroups);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(TaskGroups taskGroups) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        taskGroups = session.get(TaskGroups.class, taskGroups.getId());
        session.delete(taskGroups);
        tx1.commit();
        session.close();
    }

}
