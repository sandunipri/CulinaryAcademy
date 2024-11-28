package lk.ijse.culinaryacademy.dto;

import lk.ijse.culinaryacademy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class StudentDTO {

    private int id;

    private String name;

    private String nic;

    private String email;

    private String Address;

    private String telno;

    private User user;
}
