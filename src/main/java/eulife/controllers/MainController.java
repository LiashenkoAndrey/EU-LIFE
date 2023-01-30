package eulife.controllers;


import eulife.domain.User;
import eulife.repositories.QuestionRepository;
import eulife.services.NewsService;
import eulife.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    private static final Logger log = LogManager.getLogger(MainController.class);

    private final NewsService newsService;

    private final UserService userService;

    public MainController(NewsService newsService, UserService userService) {
        this.newsService = newsService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String index(Model model, Authentication auth) {
        model.addAttribute("news", newsService.findAllDto());
        if (auth != null) model.addAttribute("user", auth.getPrincipal());

        return "main";
    }

    @GetMapping("/registration")
    public String getRegistrationView(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public RedirectView doRegistration(@ModelAttribute("user") User user) {
        userService.saveNewUser(user);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", defaultValue = "false") boolean isError, HttpServletRequest request, Model model) {
        String errorMessage = null;
        HttpSession httpSession = request.getSession(false);
        if (isError) {
            var exception = (AuthenticationException) httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (exception != null) errorMessage = exception.getMessage();
        }

        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

}
