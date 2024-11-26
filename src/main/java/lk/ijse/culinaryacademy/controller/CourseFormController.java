package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.model.Course;
import lk.ijse.culinaryacademy.model.Student;
import lk.ijse.culinaryacademy.view.tdm.CourseTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

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
    private TableColumn<?, ?> colAction;

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
    private TableView<CourseTm> CourseTableView;

    ObservableList<CourseTm> courseList = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
        loadCourse();
    }

    private void loadCourse() {
        CourseTableView.getItems().clear();
        Session session = SessionFactoryConfig.getInstance().getSession();

        List<Course> courses= session.createQuery("FROM Course ", Course.class).getResultList();



        for (Course course : courses) {

            JFXButton btn = createButton(course.getId());

            CourseTm coursesTm = new CourseTm(
                    course.getId(),
                    course.getName(),
                    course.getDuration(),
                    course.getPrice(),
                    course.getDescription(),
                    btn);
            courseList.add(coursesTm);
        }
        CourseTableView.setItems(courseList);
    }

    private JFXButton createButton(int id) {
        JFXButton btn = new JFXButton("Delete");
        btn.setStyle("-fx-background-color: #000B58; -fx-text-fill: white;" );
        //alert for the before delete button
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (type.orElse(no) == yes) {
                Session session = SessionFactoryConfig.getInstance().getSession();
                Transaction transaction = session.beginTransaction();
                Course course = session.get(Course.class, id);
                session.delete(course);
                transaction.commit();
                session.close();
                loadCourse();
            }
        });
        return btn;
    }

    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colCoursePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCourseDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

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

        loadCourse();
        new Alert(Alert.AlertType.INFORMATION, "Course added successfully").show();

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
