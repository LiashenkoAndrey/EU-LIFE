package eulife.controllers;


import eulife.domain.Question;
import eulife.domain.Role;
import eulife.domain.User;
import eulife.repositories.QuestionRepository;
import eulife.repositories.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public IndexController(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public String index(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "start";
    }

    @GetMapping("/log-in")
    public String logIn() {
        return "logIn";
    }

    @GetMapping("/registration")
    public String getRegistrationView(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public RedirectView doRegistration(@ModelAttribute("user") User user) {

        // add default role
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));

        user.setDate_of_creation(new Date());
        user.setRole(roles);

        // encoding user's password
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return new RedirectView("/");
    }

}
