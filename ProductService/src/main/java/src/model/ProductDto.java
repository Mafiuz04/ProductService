package src.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private Set<ProductAttributesDTO> attributes = new HashSet<>();
}
