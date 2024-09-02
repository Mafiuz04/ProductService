package src.mapper;

import org.mapstruct.Mapper;
import src.model.ProductAttributes;
import src.model.ProductAttributesDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductAttributesMapper {

    List<ProductAttributesDTO> setToDTO(List<ProductAttributes> attributes);

    ProductAttributesDTO toDto(ProductAttributes attributes);

    ProductAttributes toEntity(ProductAttributesDTO attributesDTO);
}
