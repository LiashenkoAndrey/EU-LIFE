package eulife.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "start";
    }

    @GetMapping("/log-in")
    public String logIn() {
        return "logIn";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

}
