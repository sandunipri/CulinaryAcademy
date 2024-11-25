package lk.ijse.culinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    @FXML
    private Button home;

    @FXML
    private Button admin;

    @FXML
    private Button course;

    @FXML
    private Button payment;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane rootNodeDashBoardSide;

    @FXML
    private AnchorPane rootnodeDB;

    @FXML
    private AnchorPane rootnodeDashBoard;

    @FXML
    private Button student;

    public void initialize() {
        mainForm();
    }


    @FXML
    void btnAdminOnAction(ActionEvent event) {

    }

    @FXML
    void btnCourseOnAction(ActionEvent event) {
        AnchorPane anchorPane= null;
        try {
            anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/course-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.rootnodeDB.getChildren().clear();
        this.rootnodeDB.getChildren().add(anchorPane);

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        AnchorPane anchorPane= null;
        try {
            anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/PlacePayment-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.rootnodeDB.getChildren().clear();
        this.rootnodeDB.getChildren().add(anchorPane);

    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        AnchorPane anchorPane= null;
        try {
            anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/StudentManage-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.rootnodeDB.getChildren().clear();
        this.rootnodeDB.getChildren().add(anchorPane);

    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        mainForm();
    }

    private void mainForm() {
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/main-form.fxml"));
            this.rootnodeDB.getChildren().clear();
            this.rootnodeDB.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/login-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) rootnodeDashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
     }
}

