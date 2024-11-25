package lk.ijse.culinaryacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_email")
    private String email;

    @Column(name = "tel_no")
    private String telno;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String password;

}
