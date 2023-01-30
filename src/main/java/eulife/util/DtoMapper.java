package eulife.util;

import eulife.domain.dto.UserDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    default String emptyStringToNull(String str) {
        return str.isEmpty() ? null : str;
    }

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
    void setEmptyStringToNull(UserDto userDto, @MappingTarget UserDto userDto2);
}
