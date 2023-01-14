package eulife.controllers;

import eulife.domain.Comment;
import eulife.domain.Question;
import eulife.domain.User;
import eulife.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
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


    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
        model.addAttribute("question", questionRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("comment", new Comment());
        return "question";
    }

}
