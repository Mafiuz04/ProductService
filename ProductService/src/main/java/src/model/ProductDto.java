package src.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private Set<ProductAttributesDTO> attributes = new HashSet<>();
}
