package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CourseBO;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dto.CourseDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextFeid;
import lk.ijse.culinaryacademy.view.tdm.CourseTm;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.loader.collection.OneToManyJoinWalker;

import java.sql.SQLException;
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

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    public void initialize(){
        setCellValueFactory();
        loadCourse();
    }

    private void loadCourse() {
        CourseTableView.getItems().clear();


        List<CourseDTO> courseDTOS = courseBO.getAllCourses();

        for (CourseDTO courseDTO : courseDTOS) {

            JFXButton btn = createButton(courseDTO.getId());

            CourseTm coursesTm = new CourseTm(
                    courseDTO.getId(),
                    courseDTO.getName(),
                    courseDTO.getDuration(),
                    courseDTO.getPrice(),
                    courseDTO.getDescription(),
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
        CourseDTO courseDTO = new CourseDTO(1, name, duration, price, description);
        if (isValid()) {

            courseBO.addCourse(courseDTO);

            loadCourse();
            new Alert(Alert.AlertType.INFORMATION, "Course added successfully").show();

        }else {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        }

        clearFields();

    }

    @FXML
    void btnSearchCoursesOnAction(ActionEvent event) {
        int id = Integer.parseInt(courseid.getText());
        CourseDTO courseDTO = courseBO.searchByID(String.valueOf(id));
        if (courseDTO != null){
            coursename.setText(courseDTO.getName());
            courseduration.setText(courseDTO.getDuration());
            courseprice.setText(String.valueOf(courseDTO.getPrice()));
            coursedescription.setText(courseDTO.getDescription());
        }


    }

    @FXML
    void btnUpdateCoursesOnAction(ActionEvent event) {
        String name = coursename.getText();
        String duration = courseduration.getText();
        double price = Double.parseDouble(courseprice.getText());
        String description = coursedescription.getText();


        CourseDTO courseDTO = new CourseDTO(1, name, duration, price, description);

        try{
            boolean isupdate=  courseBO.update(courseDTO);
            if (isupdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Course updated!").show();

                loadCourse();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"something went wrong ").show();
        }
        clearFields();

    }

    private void clearFields() {
        courseid.clear();
        coursename.clear();
        courseduration.clear();
        courseprice.clear();
        coursedescription.clear();
    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.description, coursedescription)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.duration, courseduration)) {
            return false;
        }
        if(!Regex.setTextColor(TextFeid.Price, courseprice)){
            return false;
        }
        if (!Regex.setTextColor(TextFeid.name, coursename)) {
            return false;
        }

        return true;
    }


    public void txtCourseNameKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.name, coursename)) {}
    }

    public void txtCourseDurationKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.duration, courseduration)) {}

    }

    public void txtCourseDescriptiionKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.description, coursedescription)) {}

    }

    public void txtCoursepriceKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Price, courseprice)) {}

    }
}
