package eulife.controllers;


import eulife.domain.Question;
import eulife.domain.User;
import eulife.repositories.QuestionRepository;
import eulife.services.UserService;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    public MainController(QuestionRepository questionRepository, UserService userService) {
        this.questionRepository = questionRepository;
        this.userService = userService;
    }


    @GetMapping("/")
    public String index(Model model, Authentication auth) {

//        model.addAttribute("authenticated", auth != null);
        if (auth != null) model.addAttribute("user", (User) auth.getPrincipal());

        model.addAttribute("userAuthenticated", auth != null);
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "main";
    }

    @GetMapping("/error")
    public String showError() {
        return "error_page";
    }

    @GetMapping("/registration")
    public String getRegistrationView(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public RedirectView doRegistration(@ModelAttribute("user") User user) {
        userService.save(user);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

}
