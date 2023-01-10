package eulife.controllers;


import eulife.domain.Question;
import eulife.repositories.QuestionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public List<Question> questions() {
        return questionRepository.findAll();
    }


    @GetMapping("/new")
    public String newQuestion() {
        return
    }
}
