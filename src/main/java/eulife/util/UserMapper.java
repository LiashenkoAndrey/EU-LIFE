package eulife.util;


import eulife.domain.User;
import eulife.domain.UserDetails;
import eulife.domain.dto.UserDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
    void updateUserDetailsFromDto(UserDto userDto, @MappingTarget UserDetails user);
}
