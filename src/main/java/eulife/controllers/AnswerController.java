package eulife.controllers;

import eulife.domain.Answer;
import eulife.domain.User;
import eulife.repositories.AnswerRepository;
import eulife.repositories.QuestionRepository;
import eulife.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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


//    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/new")
    public RedirectView newAnswer(
            @RequestParam("text") String text,
            @RequestParam("question_id") Long question_id,
            Authentication auth)  {

        Answer answer = new Answer();
        answer.setText(text);
        answer.setDate_of_creation(new Date());
        answer.setAuthor((User) auth.getPrincipal());
        answer.setQuestion(questionRepository.findById(question_id).get());

        answerRepository.save(answer);
        return new RedirectView("/question/" + question_id);
    }
}
