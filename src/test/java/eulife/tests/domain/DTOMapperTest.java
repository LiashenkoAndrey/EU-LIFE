package eulife.tests.domain;

import eulife.domain.User;
import eulife.domain.UserDetails;
import eulife.domain.dto.UserDto;
import eulife.tests.UserUtils;
import eulife.util.DtoMapper;
import eulife.util.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DTOMapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DtoMapper dtoMapper;

    @Test
    public void doUserMapper() {
        User user = UserUtils.getDefaultUser();

        UserDto userDto = new UserDto();
        userDto.setFirst_name("Andrey");
        userDto.setLast_name("Liashenko");
        userDto.setEmail("andrey@gmail.com");

        User expected = UserUtils.getDefaultUser();
        expected.setFirst_name("Andrey");
        expected.setLast_name("Liashenko");
        expected.setEmail("andrey@gmail.com");
        userMapper.updateUserFromDto(userDto, user);
        Assertions.assertEquals(user, expected);
    }

    @Test
    public void testUserDetailsMapper() {
        UserDetails userDetails = UserUtils.getDefaultUserDetails();

        UserDto userDto = new UserDto();
        userDto.setLinkedin("linkedin");
        userDto.setFaculty("fist");

        UserDetails expexted = UserUtils.getDefaultUserDetails();
        expexted.setLinkedin("linkedin");
        expexted.setFaculty("fist");

        userMapper.updateUserDetailsFromDto(userDto, userDetails);
        Assertions.assertEquals(userDetails, expexted);
    }

    @Test
    public void testIgnoreEmptyString() {
        User user = UserUtils.getDefaultUser();
        user.setFirst_name("Andrey");
        user.setLast_name("Liashenko");

        UserDto userDto = new UserDto();
        userDto.setFirst_name("");
        userDto.setLast_name("");
        userDto.setEmail("andrey@gmail.com");

        User expected = UserUtils.getDefaultUser();
        expected.setFirst_name("Andrey");
        expected.setLast_name("Liashenko");
        expected.setEmail("andrey@gmail.com");

        UserDto newUserDto = new UserDto();
        dtoMapper.setEmptyStringToNull(userDto, newUserDto);
        userMapper.updateUserFromDto(newUserDto, user);
        Assertions.assertEquals(user, expected);
    }

 }
