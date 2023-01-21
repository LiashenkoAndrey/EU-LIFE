package eulife.dto_test;


import eulife.domain.Image;
import eulife.domain.Role;
import eulife.domain.User;
import eulife.domain.UserDetails;
import eulife.domain.dto.UserDetailsDto;
import eulife.domain.dto.UserDto;
import eulife.repositories.UserRepository;
import eulife.util.UserDetailsMapper;
import eulife.util.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DTOMapperTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDetailsMapper userDetailsMapper;

    @Test
    public void doTest() {


        ByteArrayOutputStream bos;
        File file;
        try {
            file = new File("src/main/resources/static/img/download.png");
            BufferedImage bImage = ImageIO.read(file);
            bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(file.getName(), bos.toByteArray());

        User user = userRepository.findById(12L).get();
        user.getUser_details().getImage().setFilename(image.getFilename());
        user.getUser_details().getImage().setBinary_image(image.getBinary_image());
//
//        UserDetailsDto userDetailsDto = new UserDetailsDto();
//        userDetailsDto.setUniversity("EU");
//        userDetailsDto.setFaculty("FIST");
//        userDetailsDto.setLinkedin("link");
//        userDetailsDto.setSite("site");
//        userDetailsDto.setGithub("GITHUB");
//        userDetailsDto.setImage(image);
//        userDetailsMapper.updateUserDetailsFromDto(userDetailsDto, user.getUser_details());
        userRepository.save(user);
    }
 }
