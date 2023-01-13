package eulife.controllers;

import eulife.repositories.ArticleRepository;
import eulife.repositories.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleController(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }


    @GetMapping("/{id}")
    public String showArticle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleRepository.findById(id));
        model.addAttribute("comments", commentRepository.findCommentsByArticle_Id(id));
        return "article";
    }

}
