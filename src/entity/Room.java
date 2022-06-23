package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room implements SuperEntity {
    @Id
    private String room_id;
    private String room_type;
    private double monthly_rent;
    private int room_qty;
}
