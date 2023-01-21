package eulife.services;

import eulife.domain.Image;
import eulife.domain.Role;
import eulife.domain.User;
import eulife.domain.UserDetails;
import eulife.domain.dto.UserDetailsDto;
import eulife.repositories.UserRepository;
import eulife.util.ImageMapper;
import eulife.util.UserDetailsMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final ImageMapper imageMapper;

    private final UserDetailsMapper userDetailsMapper;

    private final UserRepository userRepository;


    public UserService(ImageMapper imageMapper, UserDetailsMapper userDetailsMapper, UserRepository userRepository) {
        this.imageMapper = imageMapper;
        this.userDetailsMapper = userDetailsMapper;
        this.userRepository = userRepository;
    }

    public void updateDetails(UserDetailsDto userDetailsDto, User user) {
        userDetailsMapper.updateUserDetailsFromDto(userDetailsDto, user.getUser_details());
        userRepository.save(user);
    }
//    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		ImageIO.write(bImage, "png", bos );
    public void updateImage(MultipartFile file, User user) {
        BufferedImage buffImg;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            buffImg = cropImage(ImageIO.read(file.getInputStream()));
            byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "png", byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image newImage = new Image(file.getOriginalFilename(), byteArrayOutputStream.toByteArray());
        imageMapper.updateImage(newImage, user.getUser_details().getImage());
        userRepository.save(user);
    }

    public BufferedImage cropImage(BufferedImage buffImg) {

        // if image is square return
        if (buffImg.getWidth() == buffImg.getHeight()) return buffImg;

        int width = buffImg.getWidth();
        int height = buffImg.getWidth();

        int squareSize = Math.min(height, width);

        return buffImg.getSubimage(
                width/2 - (squareSize / 2),
                height/2 - (squareSize /2),
                squareSize,
                squareSize);
    }

    public void save(User user) {
        // add default role
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));

        user.setDate_of_creation(new Date());
        user.setRole(roles);
        UserDetails userDetails = new UserDetails();
        userDetails.setImage(getDefaultUserImage());
        user.setUser_details(userDetails);

        // encoding user's password
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Image getDefaultUserImage() {
        ByteArrayOutputStream bos;
        File file;
        try {
            file = new File("src/main/resources/static/img/DefaultUserImage.png");
            BufferedImage bImage = ImageIO.read(file);
            bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Image(file.getName(), bos.toByteArray());
    }

    public Image getImageByUserId(Long user_id) {
        return userRepository.findImageByUserId(user_id);
    }
}
