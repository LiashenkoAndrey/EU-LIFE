package eulife.util;

import eulife.domain.UserDetails;
import eulife.domain.dto.UserDetailsDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserDetailsFromDto(UserDetailsDto detailsDto, @MappingTarget UserDetails userDetails);
}
