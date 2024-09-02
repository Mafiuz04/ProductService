package src.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String attributeName;
    @Column(nullable = false)
    private String attributeValue;
    @ManyToMany(mappedBy = "attributes",cascade = CascadeType.ALL)
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
