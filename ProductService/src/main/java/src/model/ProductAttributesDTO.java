package src.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Set;

@Data
public class ProductAttributesDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    private Accessories accessories;
    @Enumerated(EnumType.STRING)
    private Colors colors;
    @Enumerated(EnumType.STRING)
    private PhoneBatteryCapacity batteryCapacity;
    @Enumerated(EnumType.STRING)
    private RAMs ram;
    @Enumerated(EnumType.STRING)
    private Processors processor;

    private Set<Long> productsId;
}
