package eulife.services;

import eulife.domain.*;
import eulife.domain.dto.UserDto;
import eulife.repositories.ImageRepository;
import eulife.repositories.UserRepository;
import eulife.util.DtoMapper;
import eulife.util.ImageMapper;
import eulife.util.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private final ImageMapper imageMapper;

    private final UserMapper userMapper;

    private final DtoMapper dtoMapper;

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;


    public UserService(ImageMapper imageMapper, UserMapper userMapper, DtoMapper dtoMapper, UserRepository userRepository, ImageRepository imageRepository) {
        this.imageMapper = imageMapper;
        this.userMapper = userMapper;
        this.dtoMapper = dtoMapper;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }


    public void updateUser(UserDto userDto, User user) {
        UserDto newUserDTO = new UserDto();
        dtoMapper.setEmptyStringToNull(userDto, newUserDTO);
        userMapper.updateUserFromDto(newUserDTO, user);
        userMapper.updateUserDetailsFromDto(newUserDTO, user.getUser_details());
        userRepository.save(user);
    }


    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        userRepository.delete(optionalUser.orElseThrow(EntityNotFoundException::new));
        log.info("User with id: "+ optionalUser.get().getId() +" was deleted from db!");
    }


    public void setUserNotLockedById(Long id, boolean notLocked) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setNot_locked(notLocked);
        userRepository.save(user);
        log.info("User with id: "+ user.getId() +", notLocked: " + notLocked);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

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

    public User saveNewUser(User user) {
        user.setDate_of_creation(new Date());
        user.setRole(Role.defaultRole());
        user.setNot_locked(true);
        UserDetails userDetails = new UserDetails();
        userDetails.setImage(getDefaultUserImage());
        user.setUser_details(userDetails);

        // encoding user's password
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public Image getDefaultUserImage() {
        return new Image("defaulUserImage", imageRepository.getImageById("63e64b899552524cf104c663"));

    }

    public Image getImageByUserId(Long user_id) {
        return userRepository.findImageByUserId(user_id);
    }

    public List<User> getAll() {
        log.info("Getting all users!");
        return userRepository.findAll();
    }

    public boolean checkLogin(String login) {
        return userRepository.checkLogin(login);
    }

    public Page<User> getPageOfUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


}
