package eulife.controllers;

import eulife.domain.Image;
import eulife.domain.User;
import eulife.domain.dto.UserDto;
import eulife.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/image/get/{user_id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("user_id") Long user_id) {
        Image image = userService.getImageByUserId(user_id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image.getBinary_image());
    }

    @PostMapping("/image/update")
    public String updateImage(@RequestParam("file") MultipartFile file, Authentication auth) {
        userService.updateImage(file, (User) auth.getPrincipal());
        return "redirect:/user/edit";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute("user_dto") UserDto userDto, Authentication auth) {
        userService.updateUser(userDto ,(User) auth.getPrincipal());
        return "redirect:/user/edit";
    }


    @GetMapping("/edit")
    public String edit(Model model, Authentication auth) {
        model.addAttribute("user", auth.getPrincipal());
        model.addAttribute("update_user", new UserDto());
        return "user_page/edit";
    }


    @GetMapping("/delete")
    public String delete() {
        return "user_page/delete";
    }

    @GetMapping("/notification")
    public String notificationSettings() {
        return "user_page/notification";
    }

    @GetMapping("/password")
    public String changePassword() {
        return "user_page/password";
    }

    @GetMapping("/theme")
    public String editTheme() {
        return "user_page/theme";
    }

    @GetMapping("/cookies")
    public String editCookies() {
        return "user_page/cookies";
    }
}
