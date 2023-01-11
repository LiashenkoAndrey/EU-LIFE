package eulife.controllers;

import eulife.domain.Answer;
import eulife.repositories.AnswerRepository;
import eulife.repositories.QuestionRepository;
import eulife.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    private final QuestionRepository questionRepository;

    public AnswerController(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }


    @PostMapping("/new")
    public RedirectView newAnswer(
            @RequestParam("author_id") Long author_id,
            @RequestParam("text") String text,
            @RequestParam("question_id") Long question_id)  {


        Answer answer = new Answer(
                text,
                questionRepository.findById(question_id).get(),
                userRepository.findById(author_id).get(),
                new Date());

        answerRepository.save(answer);

        return new RedirectView("/question/" + question_id);
    }
}
