package eulife.controllers;


import eulife.domain.Question;
import eulife.repositories.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final QuestionRepository questionRepository;

    public IndexController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
    public String registration() {
        return "registration";
    }

}
