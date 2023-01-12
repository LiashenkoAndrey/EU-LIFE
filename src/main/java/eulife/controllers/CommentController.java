package eulife.controllers;


import eulife.domain.Comment;
import eulife.domain.User;
import eulife.repositories.AnswerRepository;
import eulife.repositories.CommentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    public CommentController(CommentRepository commentRepository, AnswerRepository answerRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }


    @PostMapping("/new")
    public RedirectView newComment(
            @ModelAttribute("comment") Comment comment,
            @RequestParam(value = "answer_id", required = false) Long answer_id,
            @RequestParam(value = "comment_id", required = false) Long comment_id,
            Authentication auth) {

        comment.setAuthor((User) auth.getPrincipal());
        if (comment_id != null) comment.setComment(commentRepository.findById(comment_id).get());
        if (answer_id != null) comment.setAnswer(answerRepository.findById(answer_id).get());
        comment.setDate_of_creation(new Date());

        commentRepository.save(comment);

        return new RedirectView("/");
    }
}
