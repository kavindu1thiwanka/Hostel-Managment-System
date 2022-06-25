package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reserve")
public class Reserve implements SuperEntity{
    @Id
    private String res_id;
    private String date;
    private double key_money;
    private String student_id;
    private String room_id;
}
