package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.SuperDAO;
import lk.ijse.culinaryacademy.entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO {
    void save(Student student);

    List<Student> getAll();

    Student search(String studentContact);

    void update(Student student);

    Student searchonId(int id);
}
