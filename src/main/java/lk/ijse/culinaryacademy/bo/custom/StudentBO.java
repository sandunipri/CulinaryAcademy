package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.StudentDAO;
import lk.ijse.culinaryacademy.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    void addStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();
}
