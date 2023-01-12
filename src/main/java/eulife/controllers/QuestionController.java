package eulife.controllers;

import eulife.domain.Answer;
import eulife.domain.Comment;
import eulife.domain.Question;
import eulife.domain.User;
import eulife.repositories.AnswerRepository;
import eulife.repositories.CommentRepository;
import eulife.repositories.QuestionRepository;
import eulife.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;


@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    public QuestionController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/new")
    public String newQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "newQuestion";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/new")
    public RedirectView saveQuestion(@ModelAttribute("question") Question question, Authentication auth) {
        question.setDate_of_creation(new Date());

        // getting user instance from security session
        question.setAuthor((User) auth.getPrincipal());

        questionRepository.save(question);
        return new RedirectView("/");
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable("id") Long id, Model model) {
        model.addAttribute("question", questionRepository.findById(id).get());
        model.addAttribute("answers", answerRepository.findAnswersByQuestionId(id));
        model.addAttribute("comment", new Comment());
        return "question";
    }

}
