package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.custom.CourseDAO;
import lk.ijse.culinaryacademy.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public void save(Course course) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction  = session.beginTransaction();
        session.save(course);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Course> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        return session.createQuery("FROM Course ", Course.class).getResultList();
    }
}
