package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.StudentDAO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public void addStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getNic(),
                studentDTO.getEmail(),
                studentDTO.getAddress(),
                studentDTO.getTelno(),
                studentDTO.getUser()

        );
        studentDAO.save(student);

    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> allStudents = studentDAO.getAll();
        List<StudentDTO> dtos = new ArrayList<>();;

        for (Student student : allStudents) {
            dtos.add(new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getNic(),
                    student.getEmail(),
                    student.getAddress(),
                    student.getTelno(),
                    student.getUser()
            ));
        }

        return dtos;
    }
}
