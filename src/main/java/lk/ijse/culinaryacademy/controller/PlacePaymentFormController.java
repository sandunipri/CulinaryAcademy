package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.PlacePaymentBO;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dto.CourseDTO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;
import lk.ijse.culinaryacademy.dto.StudentCourseDetailsDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.StudentCourseDetails;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextFeid;
import lk.ijse.culinaryacademy.view.tdm.PaymentTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class PlacePaymentFormController {

    @FXML
    public Button courseSearchbtn;
    public Button studentSearchbtn;

    @FXML
    private TableView<PaymentTm> PaymenttableView;

    @FXML
    private ChoiceBox<String> choiceCourse;

    @FXML
    private ChoiceBox<String> choicePaymentMethod;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colcoursename;

    @FXML
    private TableColumn<?, ?> colcourseprice;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtStudentSearch;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private TextField txtcourseduration;

    @FXML
    private TextField txtcoursename;

    @FXML
    private TextField txtcourseprice;

    Date date = new Date(System.currentTimeMillis());
    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

    CourseDTO selectedCourse = null;
    StudentDTO selectedStudent = null;

    PlacePaymentBO placePaymentBO = (PlacePaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEPAYMENT);

    public void initialize(){
        loadpaymentMethod();
        loadCourses();
        setCellValueFactory();


    }

    private void setCellValueFactory() {
        colcoursename.setCellValueFactory(new PropertyValueFactory<>("Coursename"));
        colcourseprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadCourses() {

        List<CourseDTO> courseList = placePaymentBO.getAllCourses();
        try{
            choiceCourse.getItems().clear();
            for (CourseDTO  course : courseList) {
                choiceCourse.getItems().add(course.getName());
            }
            if (courseList.isEmpty()){
                choiceCourse.setValue(courseList.get(0).getName());
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "not Found").show();
        }


    }

    private void loadpaymentMethod() {
        choicePaymentMethod.getItems().add("Cash");
        choicePaymentMethod.getItems().add("Card");
    }

    @FXML
    void btnAddToCartClickOnAction(ActionEvent event) {
        if (selectedCourse == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a course").show();
            return;
        }

        /*double amount = Double.parseDouble(txtAmount.getText());
        double totalPrice = Double.parseDouble(txtTotalPrice.getText());
        totalPrice += amount;
        txtTotalPrice.setText(String.valueOf(totalPrice));*/

        JFXButton btn = createButton(selectedCourse);

        PaymentTm selectedCourseEntry = new PaymentTm(selectedCourse.getName(),selectedCourse.getPrice(), btn);
        PaymenttableView.getItems().add(selectedCourseEntry);

        txtTotalPrice.setText(String.valueOf(selectedCourse.getPrice()));

    }

    private JFXButton createButton(CourseDTO selectedCourse) {
        JFXButton btn = new JFXButton("Remove");
        btn.setStyle("-fx-background-color: #000B58; -fx-text-fill: white;");

        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (type.orElse(no) == yes) {
                PaymenttableView.getItems().clear();
                choiceCourse.setDisable(false);
                courseSearchbtn.setDisable(false);
            }
        });

        return btn;
    }

    @FXML
    void btnCourseSearchClickOnAction(ActionEvent event) {
        String courseName = choiceCourse.getValue();

        if (courseName == null || courseName.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a course").show();
            return;
        }

            selectedCourse = placePaymentBO.searchCourse(courseName);

            txtcoursename.setText(selectedCourse.getName());
            txtcourseduration.setText(selectedCourse.getDuration());
            txtcourseprice.setText(String.valueOf(selectedCourse.getPrice()));

            new Alert(Alert.AlertType.CONFIRMATION, "Course Found").show();
            choiceCourse.setDisable(true);
            courseSearchbtn.setDisable(true);


    }

    @FXML
    void btnPayClickOnAction(ActionEvent event) {
        StudentCourseDetailsDTO studentCourseDetailsDTO = new StudentCourseDetailsDTO(1,date,selectedStudent,selectedCourse);
        double balance = selectedCourse.getPrice() - Double.parseDouble(txtAmount.getText());
        PaymentDTO paymentDTO= new PaymentDTO(1,date,choicePaymentMethod.getValue(),selectedCourse.getPrice(),balance,studentCourseDetailsDTO);

        if (isValid()){
            placePaymentBO.placepayment(studentCourseDetailsDTO,paymentDTO);

            new Alert(Alert.AlertType.INFORMATION, "Payment Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        }
        clearfeilds();


    }

    private void clearfeilds() {
        txtcoursename.clear();
        txtcourseduration.clear();
        txtcourseprice.clear();
        txtAmount.clear();
        txtTotalPrice.clear();
        txtStudentSearch.clear();
        choiceCourse.setDisable(false);
        courseSearchbtn.setDisable(false);
        selectedCourse = null;
        selectedStudent = null;
        PaymenttableView.getItems().clear();
    }


    @FXML
    void btnStudentSearchClickOnAction(ActionEvent event) {
        String studentContact = txtStudentSearch.getText();

        if (studentContact == null || studentContact.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter a student contact number").show();
            return;
        }
        selectedStudent = placePaymentBO.searchStudent(studentContact);


            if (selectedStudent == null) {
                new Alert(Alert.AlertType.ERROR, "Student not found").show();
                return;
            }

            new Alert(Alert.AlertType.INFORMATION, "Student found").show();
//            txtStudentSearch.setDisable(true);

    }


    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.name, txtcoursename)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.duration, txtcourseduration)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Price, txtcourseprice)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Amount, txtAmount)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.Amount, txtTotalPrice)) {
            return false;
        }


        return true;
    }


    public void txtCourseTelNoKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.TelNo, txtStudentSearch)) {

        }

    }

    public void txtCourseNameKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.name, txtcoursename)) {
        }
    }

    public void txtCourseDurationKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.duration, txtcourseduration)) {
        }
    }

    public void txtCoursePriceKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Price, txtcourseprice)) {
        }
    }

    public void txtTotalPriceKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Price, txtTotalPrice)) {
        }

    }

    public void txtAmountKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Amount, txtAmount)) {
        }

    }
}
