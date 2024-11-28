package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.StudentCourseDetails;
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

    Course selectedCourse = null;
    Student selectedStudent = null;


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
        Session session = SessionFactoryConfig.getInstance().getSession();

        List<Course> courseList = session.createQuery("FROM CourseDTO", Course.class).getResultList();
        try{
            choiceCourse.getItems().clear();
            for (Course course : courseList) {
                choiceCourse.getItems().add(course.getName());
            }
            if (courseList.isEmpty()){
                choiceCourse.setValue(courseList.get(0).getName());
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "not Found").show();
        }finally {
            session.close();
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

    }

    private JFXButton createButton(Course selectedCourse) {
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

        Session session = SessionFactoryConfig.getInstance().getSession();

        try{
            selectedCourse = session.createQuery("FROM CourseDTO WHERE name = :Cname", Course.class)
                    .setParameter("Cname", courseName)
                    .uniqueResult();

            txtcoursename.setText(selectedCourse.getName());
            txtcourseduration.setText(selectedCourse.getDuration());
            txtcourseprice.setText(String.valueOf(selectedCourse.getPrice()));

            new Alert(Alert.AlertType.CONFIRMATION, "Course Found").show();
            choiceCourse.setDisable(true);
            courseSearchbtn.setDisable(true);

        }catch (Exception e){
            e.printStackTrace();
           /* new Alert(Alert.AlertType.ERROR, "Course not Found").show();*/

        }finally {
            session.close();
        }

    }

    @FXML
    void btnPayClickOnAction(ActionEvent event) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        StudentCourseDetails studentCourseDetails = new StudentCourseDetails(1,date,selectedStudent,selectedCourse);
        session.save(studentCourseDetails);

        for (PaymentTm paymentTm : PaymenttableView.getItems()) {
            txtTotalPrice.setText(String.valueOf(paymentTm.getPrice()));
            double balance = selectedCourse.getPrice() - Double.parseDouble(txtAmount.getText());

            Payment payment = new Payment(1,date,choicePaymentMethod.getValue(),paymentTm.getPrice(),balance,studentCourseDetails);
            session.save(payment);
            transaction.commit();
            session.close();
            new Alert(Alert.AlertType.INFORMATION, "Payment Successful").show();

        }



    }

    @FXML
    void btnStudentSearchClickOnAction(ActionEvent event) {
        String studentContact = txtStudentSearch.getText();

        if (studentContact == null || studentContact.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter a student contact number").show();
            return;
        }

        Session session = SessionFactoryConfig.getInstance().getSession();

        try {
            selectedStudent = session.createQuery("FROM StudentDTO WHERE telno = :contact", Student.class)
                    .setParameter("contact", studentContact)
                    .uniqueResult();

            if (selectedStudent == null) {
                new Alert(Alert.AlertType.ERROR, "Student not found").show();
                return;
            }

            new Alert(Alert.AlertType.INFORMATION, "Student found").show();
//            txtStudentSearch.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error searching student").show();
        } finally {
            session.close();
        }
    }

}
