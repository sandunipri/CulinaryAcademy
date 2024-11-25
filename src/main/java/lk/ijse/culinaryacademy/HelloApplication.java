package lk.ijse.culinaryacademy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
//        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/login-form.fxml"))));
//        stage.setTitle(" ");
//        stage.centerOnScreen();
//        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}
