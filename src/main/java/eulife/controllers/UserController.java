package eulife.controllers;

import eulife.domain.Roles;
import eulife.domain.User;
import eulife.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/id")
    public Optional<User> getUsers(@RequestParam("id") Long id) {
        return userRepository.findById(id);
    }


    @GetMapping("/new")
    public void newUser() {

        User maryna = new User.UserBuilder()
                .setAge(19)
                .setEmail("maruna@gmail.com")
                .setPassword("maryna")
                .setLogin("maryna")
                .setFirst_name("maryna")
                .setLast_name("Gordon")
                .setDate_of_creation(new Date())
                .setRole(Roles.USER)
                .build();

        userRepository.save(maryna);
    }

}
