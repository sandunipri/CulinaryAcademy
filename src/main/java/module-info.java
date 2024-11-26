module lk.ijse.culinaryacademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;
    requires java.naming;
    requires javafx.graphics;
    requires com.jfoenix;
    requires javafx.base;

    exports lk.ijse.culinaryacademy;

    opens lk.ijse.culinaryacademy.controller to javafx.fxml;
    opens lk.ijse.culinaryacademy.model to org.hibernate.orm.core;
    opens lk.ijse.culinaryacademy.view.tdm to javafx.base, javafx.fxml; // Open to javafx.base
}
