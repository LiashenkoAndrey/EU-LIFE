package eulife.util;

import eulife.domain.Image;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-05T17:38:47+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public void updateImage(Image newImage, Image image) {
        if ( newImage == null ) {
            return;
        }

        if ( newImage.getFilename() != null ) {
            image.setFilename( newImage.getFilename() );
        }
        byte[] binary_image = newImage.getBinary_image();
        if ( binary_image != null ) {
            image.setBinary_image( Arrays.copyOf( binary_image, binary_image.length ) );
        }
    }
}
