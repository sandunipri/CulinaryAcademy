package lk.ijse.culinaryacademy.view.tdm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CourseTm {
    private int courseID;
    private String courseName;
    private String courseDuration;
    private double price;
    private String courseDescription;

}
