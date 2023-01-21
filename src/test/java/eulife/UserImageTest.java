package eulife;


import eulife.domain.Image;
import eulife.repositories.ImageRepository;
import eulife.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserImageTest {



    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void test() {
//
        Image image1 = new Image();
        System.out.println(image1.getId());
////        System.out.println(image.getFilename());
////        Image image1 = imageRepository.findById(1L).get();
//        UserDetails userDetails = userDetailsRepository.findById(4L).get();
//        Image image1 = userDetails.getImage();
//        System.out.println(image1.getFilename());
//        System.out.println(image1.getBinary_image().length);

    }
}
