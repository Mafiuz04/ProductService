package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SmartphoneDto extends Product{
    private Colors color;
    private PhoneBatteryCapacity capacity;
    private Accessories accessories;
}
