package src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private Set<ProductAttributesDTO> attributes = new HashSet<>();
}
