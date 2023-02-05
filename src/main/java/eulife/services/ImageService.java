package eulife.services;

import eulife.domain.Image;
import eulife.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String saveImage(MultipartFile file) {
        String id;
        try {
            id = imageRepository.saveImage(new Image(file.getOriginalFilename(), file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public byte[] getImageById(String id) {
        return imageRepository.getImageById(id);
    }
}
