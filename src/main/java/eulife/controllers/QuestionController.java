package eulife.controllers;

import eulife.domain.Question;
import eulife.domain.User;
import eulife.repositories.AnswerRepository;
import eulife.repositories.QuestionRepository;
import eulife.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Optional;


@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public QuestionController(QuestionRepository questionRepository, AnswerRepository answerRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/new")
    public String newQuestion() {
        return "newQuestion";
    }

    @PostMapping("/new")
    public RedirectView saveQuestion(
            @RequestParam("description") String description,
            @RequestParam("text") String text,
            @RequestParam("user_id") Long user_id) {

        Optional<User> author = userRepository.findById(user_id);
        Question question = new Question(text, author.get(), new Date(), description);
        questionRepository.save(question);
        return new RedirectView("/");
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable("id") Long id, Model model) {
        Question question = questionRepository.findById(id).get();
        model.addAttribute(
                "question",
                question
        );
        model.addAttribute("author", question.getAuthor());
        model.addAttribute("answers", answerRepository.findAnswersByQuestionId(question.getId()));
        return "question";
    }

}
