package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.SuperDAO;
import lk.ijse.culinaryacademy.entity.Course;

import java.util.List;

public interface CourseDAO extends SuperDAO {
    void save(Course course);

    List<Course> getAll();

    Course search(String courseName);
}
