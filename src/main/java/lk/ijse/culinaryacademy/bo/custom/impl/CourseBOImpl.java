package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.CourseBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.CourseDAO;
import lk.ijse.culinaryacademy.dto.CourseDTO;
import lk.ijse.culinaryacademy.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public void addCourse(CourseDTO courseDTO) {
        Course course = new Course(courseDTO.getId(),courseDTO.getName(),courseDTO.getDuration()
        ,courseDTO.getPrice(),courseDTO.getDescription());
        courseDAO.save(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> allCourses = courseDAO.getAll();
        List<CourseDTO> dtos = new ArrayList<>();

        for (Course course : allCourses) {
            dtos.add(new CourseDTO(course.getId(),
                    course.getName(),
                    course.getDuration(),
                    course.getPrice(),
                    course.getDescription()
            ));
        }

        return dtos;
    }
}
