package lk.ijse.culinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PaymentDTO {

    private int paymentId;

    private Date paymentDate;

    private String paymentMethod;

    private double paymentAmount;

    private double paymentBalance;

    private StudentCourseDetailsDTO studentCourseDetails;

}
