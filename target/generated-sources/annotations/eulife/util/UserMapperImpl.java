package eulife.util;

import eulife.domain.User;
import eulife.domain.dto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-20T16:48:18+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public void updateUserFromDto(UserDto userDto, User user) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getFirst_name() != null ) {
            user.setFirst_name( userDto.getFirst_name() );
        }
        if ( userDto.getLast_name() != null ) {
            user.setLast_name( userDto.getLast_name() );
        }
        if ( userDto.getEmail() != null ) {
            user.setEmail( userDto.getEmail() );
        }
        if ( userDto.getPassword() != null ) {
            user.setPassword( userDto.getPassword() );
        }
    }
}
