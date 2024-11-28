package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dto.CourseDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.User;
import lk.ijse.culinaryacademy.view.tdm.StudentTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class StudentManageFormController {
    @FXML
    private TableView<StudentTm> StudentTableView;

    @FXML
    private TableColumn<?, ?> colAction;
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

    User user = LoginFormController.user;

    ObservableList<StudentTm> studentList = FXCollections.observableArrayList();

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        setCellValueFactory();
        loadStudent();
    }

    private void loadStudent() {
        StudentTableView.getItems().clear();
/*
        Session session = SessionFactoryConfig.getInstance().getSession();
*/

/*
        List<Student> students = session.createQuery("FROM Student", Student.class).getResultList();
*/
        List<StudentDTO> studentDTOS = studentBO.getAllStudents();

        for (StudentDTO studentDTO : studentDTOS) {

            JFXButton btn = createButton(studentDTO.getId());

            StudentTm studentTm = new StudentTm(
                    studentDTO.getId(),
                    studentDTO.getName(),
                    studentDTO.getNic(),
                    studentDTO.getEmail(),
                    studentDTO.getAddress(),
                    studentDTO.getTelno(),
                    btn
            );
            studentList.add(studentTm);
        }
        StudentTableView.setItems(studentList);

    }

    private JFXButton createButton(int id) {
        JFXButton btn = new JFXButton("Delete");
        btn.setStyle("-fx-background-color: #000B58;  -fx-text-fill: white;");

          btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (type.orElse(no) == yes) {
                Session session = SessionFactoryConfig.getInstance().getSession();
                Transaction transaction = session.beginTransaction();
                Student student = session.get(Student.class, id);
                session.delete(student);
                transaction.commit();
                session.close();
                loadStudent();
            }
        });
        return btn;
    }

    private void setCellValueFactory() {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colStudentNic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colStudentTelNo.setCellValueFactory(new PropertyValueFactory<>("TelNo"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    @FXML
    void btnStudentAddClickOnAction(ActionEvent event) {
        String address = studentAddress.getText();
        String name = studentName.getText();
        String email = studentemail.getText();
        String nic = studentnic.getText();
        String telNo = studenttelno.getText();

        StudentDTO studentDTO = new StudentDTO(1, name, nic, email, address, telNo, user);

        studentBO.addStudent(studentDTO);

        loadStudent();
        new Alert(Alert.AlertType.INFORMATION, "Student added successfully").show();
        clearFields();



    }

    @FXML
    void btnStudentDeleteClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentSearchClickOnAction(ActionEvent event) {
        int id = Integer.parseInt(studentid.getText());
        StudentDTO studentDTO  = studentBO.searchByID(String.valueOf(id));
        if (studentDTO != null){
            studentName.setText(studentDTO.getName());
            studentnic.setText(studentDTO.getNic());
            studentemail.setText(studentDTO.getEmail());
            studentAddress.setText(studentDTO.getAddress());
            studenttelno.setText(studentDTO.getTelno());
        }
    }

    @FXML
    void btnStudentUpdateClickOnAction(ActionEvent event) {
        String name = studentName.getText();
        String email = studentemail.getText();
        String address = studentAddress.getText();
        String nic = studentnic.getText();
        String telNo = studenttelno.getText();


        StudentDTO studentDTO = new StudentDTO(1, name, nic, email, address, telNo, user);

        try{
            boolean isupdate=  studentBO.update(studentDTO);
            if (isupdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Course updated!").show();

                loadStudent();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"something went wrong ").show();
        }
        clearFields();

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
