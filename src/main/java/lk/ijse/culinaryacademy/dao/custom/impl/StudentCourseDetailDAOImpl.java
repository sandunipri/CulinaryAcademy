package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.dao.custom.StudentCourseDetailDAO;
import lk.ijse.culinaryacademy.entity.StudentCourseDetails;
import org.hibernate.Session;

public class StudentCourseDetailDAOImpl implements StudentCourseDetailDAO {
    @Override
    public void save(Session session, StudentCourseDetails studentCourseDetails) {
        session.save(studentCourseDetails);
    }
}
