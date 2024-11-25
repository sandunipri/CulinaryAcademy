package lk.ijse.culinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.model.Course;
import lk.ijse.culinaryacademy.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseFormController {
    @FXML
    private TableColumn<?, ?> colCourseDescription;

    @FXML
    private TableColumn<?, ?> colCourseDuration;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colCoursePrice;

    @FXML
    private TextField coursedescription;

    @FXML
    private TextField courseduration;

    @FXML
    private TextField courseid;

    @FXML
    private TextField coursename;

    @FXML
    private TextField courseprice;


    @FXML
    void btnAddCoursesOnAction(ActionEvent event) {
        String name = coursename.getText();
        String duration = courseduration.getText();
        double price = Double.parseDouble(courseprice.getText());
        String description = coursedescription.getText();

        Course course = new Course(1, name, duration, price, description);

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction  = session.beginTransaction();
        session.save(course);
        transaction.commit();
        session.close();

        clearFields();

    }

    @FXML
    void btnDeleteCoursesOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCoursesOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCoursesOnAction(ActionEvent event) {

    }

    private void clearFields() {
        courseid.clear();
        coursename.clear();
        courseduration.clear();
        courseprice.clear();
        coursedescription.clear();
    }


}
