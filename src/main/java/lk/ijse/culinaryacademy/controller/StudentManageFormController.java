package lk.ijse.culinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.model.Student;
import lk.ijse.culinaryacademy.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentManageFormController {

    @FXML
    private TableColumn<?, ?> colStudentAddress;

    @FXML
    private TableColumn<?, ?> colStudentEmail;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colStudentNic;

    @FXML
    private TableColumn<?, ?> colStudentTelNo;

    @FXML
    private AnchorPane rootNodeSM;

    @FXML
    private TextField studentAddress;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentemail;

    @FXML
    private TextField studentid;

    @FXML
    private TextField studentnic;

    @FXML
    private TextField studenttelno;

    static User user = new User("sanduni@gmail.com","0764389984","sanduni","1234");

    @FXML
    void btnStudentAddClickOnAction(ActionEvent event) {
        String address = studentAddress.getText();
        String name = studentName.getText();
        String email = studentemail.getText();
        String nic = studentnic.getText();
        String telNo = studenttelno.getText();

        Student student = new Student(1, name, nic, email, address, telNo, user);

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();

        clearFields();



    }

    @FXML
    void btnStudentDeleteClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentSearchClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentUpdateClickOnAction(ActionEvent event) {

    }

    private void clearFields() {
        studentid.clear();
        studentName.clear();
        studentnic.clear();
        studentemail.clear();
        studentAddress.clear();
        studenttelno.clear();

    }
}
