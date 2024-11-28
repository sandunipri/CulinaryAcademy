package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.custom.StudentDAO;
import lk.ijse.culinaryacademy.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void save(Student student) {
        Session session = SessionFactoryConfig.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Student> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        return session.createQuery("from Student",Student.class).getResultList();
    }

    @Override
    public Student search(String studentContact) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        return session.createQuery("FROM Student WHERE telno = :telNo", Student.class)
                    .setParameter("telNo", studentContact)
                    .uniqueResult();
    }

    @Override
    public void update(Student student) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
    }

    @Override
    public Student searchonId(int id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        return session.createQuery("FROM Student WHERE id = :id", Student.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}
