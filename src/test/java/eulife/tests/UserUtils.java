package eulife.tests;

import eulife.domain.User;
import eulife.domain.UserDetails;
import eulife.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Date;

public class UserUtils {

    @Autowired
    UserService userService;

    public static User getDefaultUser() {
        User user = new User();
        user.setFirst_name("test");
        user.setLast_name("test");
        user.setLogin("test");
        user.setPassword("test");
        user.setNot_locked(true);
        user.setEmail("test@test");
        Date date = new Date();
        date.setTime(1111);
        user.setDate_of_creation(date);
        return user;
    }

    public static UserDetails getDefaultUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setSite("test");
        userDetails.setUniversity("test");
        userDetails.setAge("19");
        userDetails.setFaculty("test");
        userDetails.setGithub("test");
        userDetails.setLinkedin("test");
        return userDetails;
    }


    public static Example<User> getExampleOfDefaultUser() {
        return Example.of(getDefaultUser());
    }

}
