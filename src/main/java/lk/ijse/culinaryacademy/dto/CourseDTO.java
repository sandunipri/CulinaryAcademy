package lk.ijse.culinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class CourseDTO {

    private int id;

    private String name;

    private String duration;

    private double price;

    private String description;

}
