package eulife.controllers;


import eulife.services.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public String saveImage(@RequestParam("file") MultipartFile file) {
        return imageService.saveImage(file);

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> showImage(@PathVariable("id") String id) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageService.getImageById(id));
    }
}
