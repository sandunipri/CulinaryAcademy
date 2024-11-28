package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.PlacePaymentBO;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.CourseDAO;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.dao.custom.StudentCourseDetailDAO;
import lk.ijse.culinaryacademy.dao.custom.StudentDAO;
import lk.ijse.culinaryacademy.dto.CourseDTO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;
import lk.ijse.culinaryacademy.dto.StudentCourseDetailsDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.StudentCourseDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PlacePaymentBOImpl implements PlacePaymentBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    StudentCourseDetailDAO studentCourseDetailDAO = (StudentCourseDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTCOURSEDETAILS);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

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

    @Override
    public CourseDTO searchCourse(String courseName) {
        Course course= courseDAO.search(courseName);
        return new CourseDTO(course.getId(),
                course.getName(),
                course.getDuration(),
                course.getPrice(),
                course.getDescription()
        );
    }

    @Override
    public StudentDTO searchStudent(String studentContact) {
        Student student= studentDAO.search(studentContact);
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getNic(),
                student.getEmail(),
                student.getAddress(),
                student.getTelno(),
                student.getUser()
        );
    }

    @Override
    public void placepayment(StudentCourseDetailsDTO studentCourseDetailsDTO, PaymentDTO paymentDTO) {

        StudentDTO studentDTO = studentCourseDetailsDTO.getStudent();
        CourseDTO courseDTO = studentCourseDetailsDTO.getCourses();

        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getNic(),
                studentDTO.getEmail(),
                studentDTO.getAddress(),
                studentDTO.getTelno(),
                studentDTO.getUser()
        );

        Course course = new Course(
                courseDTO.getId(),
                courseDTO.getName(),
                courseDTO.getDuration(),
                courseDTO.getPrice(),
                courseDTO.getDescription()
        );

        StudentCourseDetails studentCourseDetails = new StudentCourseDetails(
                studentCourseDetailsDTO.getStudentCourseDetailID(),
                studentCourseDetailsDTO.getRegDate(),
                student,
                course
        );

        Payment payment =new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getPaymentAmount(),
                paymentDTO.getPaymentBalance(),
                studentCourseDetails
        );



        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        /*session.save(studentCourseDetails);
        session.save(payment);*/

        studentCourseDetailDAO.save(session,studentCourseDetails);
        paymentDAO.save(session,payment);

        transaction.commit();
        session.close();

    }
}
