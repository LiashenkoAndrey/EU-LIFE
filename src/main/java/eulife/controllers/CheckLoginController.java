package eulife.controllers;

import eulife.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckLoginController {
    private final UserService userService;

    public CheckLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/check/{login}")
    public Boolean checkLogin(@PathVariable String login) {
        return userService.checkLogin(login);
    }

}
