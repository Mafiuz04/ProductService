package src.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductAttributesDTO {
    private Long id;
    private String type;
    private String attributeName;
    private String attributeValue;
}
