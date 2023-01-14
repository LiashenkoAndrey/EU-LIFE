package eulife.controllers;

import eulife.domain.Article;
import eulife.domain.User;
import eulife.repositories.ArticleRepository;
import eulife.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    public ArticleController(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }


    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        return "newArticle";
    }



    @PostMapping("/new")
    public RedirectView postArticle(@ModelAttribute("article") Article article, Authentication auth) {
        article.setDate_of_creation(new Date());
        article.setAuthor((User) auth.getPrincipal());

        articleRepository.save(article);
        return new RedirectView("/");
    }

    @GetMapping("/all")
    public String getArticles (@RequestParam(value = "page", required = false, defaultValue = "0") Integer pageIndex,
                                      @PageableDefault(size = 2)Pageable pageable,
                                      Model model) {
        Page<Article> articlePages = articleRepository.findAll(pageable.withPage(pageIndex));
        model.addAttribute("articles", articlePages.toList());
        model.addAttribute("pagesQuantity", articlePages.getTotalPages()-1);
        System.out.println(articlePages.toList());
        System.out.println(articlePages.getTotalPages());

        return "ArticlesList";
    }


    @GetMapping("/{id}")
    public String showArticle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("comments",commentRepository.findCommentsByArticle_Id(id));
        return "article";
    }

}
