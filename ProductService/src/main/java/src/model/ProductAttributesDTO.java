package src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ProductAttributesDTO {
    private Long id;
    private String type;
    private String attributeName;
    private String attributeValue;
}
