package eulife.controllers;


import eulife.domain.Comment;
import eulife.domain.CustomDate;
import eulife.domain.User;
import eulife.repositories.ArticleRepository;
import eulife.repositories.CommentRepository;
import eulife.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.NotActiveException;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;

    private final ArticleRepository articleRepository;

    public CommentController(CommentRepository commentRepository, QuestionRepository questionRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.questionRepository = questionRepository;
        this.articleRepository = articleRepository;
    }


    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public RedirectView newComment(
            @ModelAttribute("comment") Comment comment,
            @RequestParam(value = "question_id", required = false) Long question_id,
            @RequestParam(value = "comment_id", required = false) Long comment_id,
            @RequestParam(value = "article_id", required = false) Long article_id,
            Authentication auth) {

        assert auth != null;

        comment.setAuthor((User) auth.getPrincipal());
        if (question_id != null) comment.setQuestion(questionRepository.findById(question_id).orElseThrow(EntityNotFoundException::new));
        if (article_id != null) comment.setArticle(articleRepository.findById(article_id).orElseThrow(EntityNotFoundException::new));
        if (comment_id != null) comment.setComment(commentRepository.findById(comment_id).orElseThrow(EntityNotFoundException::new));
        comment.setDate_of_creation(new CustomDate());


        commentRepository.save(comment);

        return new RedirectView("/");
    }
}
