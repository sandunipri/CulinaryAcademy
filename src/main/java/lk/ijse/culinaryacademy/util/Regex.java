package lk.ijse.culinaryacademy.util;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {


    private static boolean isTextFieldValid(TextFeid textField, String text) {
        String field ="";

        switch (textField) {
            case Email :
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case TelNo :
                field = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case Price:
                field = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case Address:
                field =  "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
                break;
            case Balance:
                field ="^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case Amount:
                field = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case duration:
                field = "^[A-z|\\s]{3,}$";
                break;
            case description:
                field = "^[A-z|\\s]{3,}$";
                break;
            case name:
                field = "^[A-z|\\s]{3,}$";
                break;
            case nic:
                field = "^([0-9]){9}[V|v]$";
                break;
            case password:
                field = "^[A-z|0-9]{3,}$";
                break;


        }

        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        } else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean setTextColor(TextFeid location, TextField field){
        if (isTextFieldValid(location, field.getText())) {
            field.setStyle("-fx-border-color: blue");
            return true;
        } else {
            field.setStyle("-fx-border-color: red");
            return false;
        }
    }

    }
   /* public static boolean setTextColor(TextField field) {
        if (isTextFieldValid(field, field.getText())) {
            field.setStyle("-fx-border-color: blue");
            return true;
        } else {
            field.setStyle("-fx-border-color: red");
            return false;
        }
    }*/




