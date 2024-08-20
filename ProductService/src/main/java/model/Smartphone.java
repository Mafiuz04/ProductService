package model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Smartphone extends Product{

    private Colors color;
    private PhoneBatteryCapacity capacity;
    private Accessories accessories;
}
