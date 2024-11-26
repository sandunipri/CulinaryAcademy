package lk.ijse.culinaryacademy.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentTm {
    private int StudentID;
    private String StudentName;
    private String NIC;
    private String Email;
    private String Address;
    private String TelNo;
    private JFXButton btn;
}
