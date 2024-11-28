package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.CourseDTO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;
import lk.ijse.culinaryacademy.dto.StudentCourseDetailsDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Student;

import java.util.List;

public interface PlacePaymentBO extends SuperBO {
    List<CourseDTO> getAllCourses();

    CourseDTO searchCourse(String courseName);

    StudentDTO searchStudent(String studentContact);

    void placepayment(StudentCourseDetailsDTO studentCourseDetailsDTO, PaymentDTO paymentDTO);

}
