package eulife.util;

import eulife.domain.UserDetails;
import eulife.domain.dto.UserDetailsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-20T16:48:18+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class UserDetailsMapperImpl implements UserDetailsMapper {

    @Override
    public void updateUserDetailsFromDto(UserDetailsDto detailsDto, UserDetails userDetails) {
        if ( detailsDto == null ) {
            return;
        }

        if ( detailsDto.getUniversity() != null ) {
            userDetails.setUniversity( detailsDto.getUniversity() );
        }
        if ( detailsDto.getFaculty() != null ) {
            userDetails.setFaculty( detailsDto.getFaculty() );
        }
        if ( detailsDto.getGithub() != null ) {
            userDetails.setGithub( detailsDto.getGithub() );
        }
        if ( detailsDto.getLinkedin() != null ) {
            userDetails.setLinkedin( detailsDto.getLinkedin() );
        }
        if ( detailsDto.getSite() != null ) {
            userDetails.setSite( detailsDto.getSite() );
        }
    }
}
