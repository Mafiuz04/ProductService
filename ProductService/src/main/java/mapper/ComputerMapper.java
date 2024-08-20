package mapper;

import model.Computer;
import model.ComputerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComputerMapper {
    ComputerDto toDto(Computer computer);
}
