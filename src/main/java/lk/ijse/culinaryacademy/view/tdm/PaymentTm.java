package lk.ijse.culinaryacademy.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTm {
    private String Coursename;
    private double price;
    private JFXButton btn;

}
