package lk.ijse.culinaryacademy.view.tdm;

import javafx.fxml.FXML;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCourseTm {
    private int PaymentId;
    private String StudentName;
    private String CourseName;
    private double price;
    private Date PaymentDate;

}
