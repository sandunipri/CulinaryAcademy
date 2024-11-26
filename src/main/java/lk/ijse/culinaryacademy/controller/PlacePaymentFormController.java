package lk.ijse.culinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PlacePaymentFormController {
    @FXML
    public ChoiceBox choiceCourse;

    @FXML
    private ChoiceBox<String> choicePaymentMethod;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtStudentSearch;

    @FXML
    private TextField txtTotalPrice;

    public void initialize(){}

    @FXML
    void btnAddToCartClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnCourseSearchClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayClickOnAction(ActionEvent event) {
    }

    @FXML
    void btnStudentSearchClickOnAction(ActionEvent event) {
    }
}
