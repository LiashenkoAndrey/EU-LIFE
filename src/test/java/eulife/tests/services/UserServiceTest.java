package eulife.tests.services;

import eulife.domain.Image;
import eulife.domain.User;
import eulife.repositories.UserRepository;
import eulife.services.UserService;
import eulife.tests.UserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveNewUser() {
        User newUser = UserUtils.getDefaultUser();
        User savedUser = userService.saveNewUser(newUser);
        Assertions.assertNotNull(savedUser);
        userRepository.delete(savedUser);
    }

    @Test
    public void cropImage() throws IOException {
        Image image = userService.getDefaultUserImage();
        var byteArrayInputStream = new ByteArrayInputStream(image.getBinary_image());
        var bufferedImage = ImageIO.read(byteArrayInputStream);
        BufferedImage croppedImage = userService.cropImage(bufferedImage);
        Assertions.assertNotNull(croppedImage);
    }

    @Test
    public void getDefaultUserImage() {
        Assertions.assertNotNull(userService.getDefaultUserImage());
    }
}
