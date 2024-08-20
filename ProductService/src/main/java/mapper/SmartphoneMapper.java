package mapper;

import model.Smartphone;
import model.SmartphoneDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SmartphoneMapper {
    SmartphoneDto toDto(Smartphone smartphone);
}
