package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ProductAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Accessories accessories;
    private Colors colors;
    private PhoneBatteryCapacity batteryCapacity;
    private RAMs ram;
    @ManyToMany(mappedBy = "attributes")
    private Set<Product> products = new HashSet<>();
}
