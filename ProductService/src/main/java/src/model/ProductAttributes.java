package src.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ProductAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(mappedBy = "attributes")
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductAttributes productAttributes)) return false;
        return id != null && id.equals(productAttributes.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
