package lk.ijse.culinaryacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "payment_balance")
    private double paymentBalance;

    @ManyToOne
    @JoinColumn(name = "student_course_detail_id")
    private StudentCourseDetails studentCourseDetails;

}
