package eulife.controllers;

import eulife.domain.Image;
import eulife.domain.User;
import eulife.domain.dto.UserDto;
import eulife.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class UserController {
    private static final Logger log = LogManager.getLogger(UserService.class);
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

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public String getAllUsers(@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                              @PageableDefault(size = 3) Pageable pageable,
                              Model model, Authentication auth) {
        //pagination
        currentPage -= 1;
        Page<User> pageOfUsers = userService.getPageOfUsers(pageable.withPage(currentPage));
        model.addAttribute("users", pageOfUsers.toList());
        model.addAttribute("pagesQuantity", pageOfUsers.getTotalPages());
        model.addAttribute("currentPage", currentPage);

        if (auth != null) model.addAttribute("user", auth.getPrincipal());
        return "user/usersAll";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable("id") Long user_id,Authentication auth, Model model) {
        if (auth != null) model.addAttribute("currentUser", auth.getPrincipal());
        Optional<User> optionalUser = userService.findById(user_id);
        if (optionalUser.isEmpty()) return "error/404";
        model.addAttribute("user", optionalUser.get());
        return "user/aboutUser";

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
        return "user/edit";
    }

    @GetMapping("/delete")
    public String delete() {
        return "user/delete";
    }

    @GetMapping("/notification")
    public String notificationSettings() {
        return "user/notification";
    }

    @GetMapping("/password")
    public String changePassword() {
        return "user/password";
    }

    @GetMapping("/theme")
    public String editTheme() {
        return "user/theme";
    }

    @GetMapping("/cookies")
    public String editCookies() {
        return "user/cookies";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/all";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/lock")
    public String setUserNotLocked(@RequestParam("user_id") Long id, @RequestParam("not_locked") boolean not_locked) {
        userService.setUserNotLockedById(id, not_locked);
        return "redirect:/user/all";
    }


}
