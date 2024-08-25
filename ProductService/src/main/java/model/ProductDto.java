package model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDto {
    private String name;
    private BigDecimal price;
    private String type;
    private Set<ProductAttributes> attributes = new HashSet<>();
}
