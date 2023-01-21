package eulife.controllers;

import eulife.domain.Comment;
import eulife.domain.Question;
import eulife.domain.User;
import eulife.services.QuestionService;
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
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/new")
    public String newQuestion(Model model, Authentication auth) {
        model.addAttribute("user", auth.getPrincipal());
        model.addAttribute("question", new Question());
        return "question/newQuestion";
    }


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/new")
    public RedirectView saveQuestion(@ModelAttribute("question") Question question, Authentication auth) {
        question.setDate_of_creation(new Date());

        // getting user instance from security session
        question.setAuthor((User) auth.getPrincipal());
        questionService.save(question);
        return new RedirectView("/");
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable("id") Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        model.addAttribute("new_comment", new Comment());
        return "question/question";
    }

}
