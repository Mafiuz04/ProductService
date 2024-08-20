package mapper;

import model.Electronics;
import model.ElectronicsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElectronicsMapper {
    ElectronicsDto toDto(Electronics electronics);
}
