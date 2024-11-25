package lk.ijse.culinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane childNode;

    @FXML
    private TextField email;

    @FXML
    private Hyperlink forgotPasswrod;

    @FXML
    private Button login;

    @FXML
    private PasswordField passwrod;

    @FXML
    private Hyperlink register;

    @FXML
    private AnchorPane rootNodeLogin;

    User user = null;



    @FXML
    void btnForgotPasswrodOnAction(ActionEvent event) {

        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/UpdatePassword-form.fxml"));
            this.childNode.getChildren().clear();
            this.childNode.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOnLogin(ActionEvent event) {

        if (checkCredentials()){
            AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/dashBoard-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) rootNodeLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        }else {


        }

    }

    private boolean checkCredentials() {
        Session session = null;

        session =SessionFactoryConfig.getInstance().getSession();
        user = session.get(User.class, email.getText());
        if (user != null) {
            if (user.getPassword().equals(passwrod.getText())) {
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                return false;
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            return false;
        }

        }


    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/register-form.fxml"));
            this.childNode.getChildren().clear();
            this.childNode.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
