package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    void addCourse(CourseDTO courseDTO);

    List<CourseDTO> getAllCourses();
}
