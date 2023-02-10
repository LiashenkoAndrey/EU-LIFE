package eulife.controllers;

import eulife.domain.*;
import eulife.services.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;
import java.util.Optional;


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
    @GetMapping("/all")
    public String getAllQuestions(@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                  @PageableDefault(size = 25) Pageable pageable,
                                  Model model, Authentication auth) {
        currentPage -= 1;

        // pagination
        Page<Question> pages = questionService.findAll(pageable, currentPage);
        model.addAttribute("questions", pages.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pagesQuantity", pages.getTotalPages()-1);

        if (auth != null) model.addAttribute("user",  auth.getPrincipal());

        return "question/questionsAll";

    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/new")
    public RedirectView saveQuestion(@ModelAttribute("question") Question question, Authentication auth) {
        question.setDate_of_creation(new CustomDate());

        // getting user instance from security session
        question.setAuthor((User) auth.getPrincipal());
        questionService.save(question);
        return new RedirectView("/");
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable("id") Long id, Authentication auth, Model model) {
        // checking if question exists else return error page
        Optional<Question> questionOptional =  questionService.findById(id);
        if (questionOptional.isEmpty()) return "error/404";
        model.addAttribute("question", questionOptional.get());

        model.addAttribute("new_comment", new Comment());
        if (auth != null) model.addAttribute("user", auth.getPrincipal());
        return "question/question";
    }

}
