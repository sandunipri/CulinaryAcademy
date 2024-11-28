package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.SuperDAO;
import lk.ijse.culinaryacademy.entity.StudentCourseDetails;
import org.hibernate.Session;

public interface StudentCourseDetailDAO extends SuperDAO {
    void save(Session session, StudentCourseDetails studentCourseDetails);
}
