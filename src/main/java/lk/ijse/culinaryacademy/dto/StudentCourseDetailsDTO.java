package lk.ijse.culinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor


public class StudentCourseDetailsDTO {

    private int studentCourseDetailID;

    private Date regDate;

    private StudentDTO student;

    private CourseDTO courses;
}
