package eulife.repositories;

import eulife.domain.Image;

public interface ImageRepository {
    String saveImage(Image image);
    byte[] getImageById(String id);


}
