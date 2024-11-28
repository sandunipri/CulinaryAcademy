package lk.ijse.culinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dto.UserDTO;
import lk.ijse.culinaryacademy.entity.User;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextFeid;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class RegisterFormController {

    @FXML
    private AnchorPane childNode;

    @FXML
    private Text password;

    @FXML
    private PasswordField passwrod;

    @FXML
    private Button register;

    @FXML
    private TextField telNo;

    @FXML
    private TextField useremail;

    @FXML
    private TextField username;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnBackOnAction(ActionEvent event) {
        AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/login-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) childNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String number = telNo.getText();
        String name = username.getText();
        String email = useremail.getText();
        String password = passwrod.getText();

        UserDTO userDTO = new UserDTO(email, number, name, password);

    if (isValid()) {
       userBO.addUser(userDTO);
        clearFields();
        btnBackOnAction(event);

        }else {
            new Alert(Alert.AlertType.ERROR, "Please fill correct details").show();
    }

    }
    private void clearFields() {
        telNo.clear();
        username.clear();
        useremail.clear();
        passwrod.clear();

    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.Email, useremail)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.password, passwrod)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.TelNo, telNo)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.name, username)) {
            return false;
        }
        return true;

    }

    public void txtuserNameKeyRealesedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.name, username)) {
        }

    }

    public void txtuserPasswordKeyRealesedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.password, passwrod)) {
        }

    }

    public void txtuserTelNoKeyRealesedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.TelNo, telNo)) {
        }

    }

    public void txtuserEmailKeyRealesedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Email, useremail)) {

        }
    }
}
