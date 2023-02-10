package eulife.controllers;

import eulife.domain.Article;
import eulife.domain.CustomDate;
import eulife.domain.User;
import eulife.repositories.CommentRepository;
import eulife.services.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentRepository commentRepository;

    public ArticleController(ArticleService articleService, CommentRepository commentRepository) {
        this.articleService = articleService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String newArticle(Model model, Authentication auth) {
        assert auth != null;
        model.addAttribute("user", auth.getPrincipal());
        model.addAttribute("article", new Article());
        return "article/newArticle";
    }


    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public RedirectView postArticle(@ModelAttribute("article") Article article, Authentication auth) {
        article.setDate_of_creation(new CustomDate());
        assert auth != null;
        article.setAuthor((User) auth.getPrincipal());
        articleService.save(article);
        return new RedirectView("/");
    }

    @GetMapping("/all")
    public String getArticles (@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                               @PageableDefault(size = 25)Pageable pageable,
                               Model model, Authentication auth) {

        // pagination
        currentPage -=1;
        Page<Article> articlePages = articleService.findPage(pageable, currentPage);
        model.addAttribute("articles", articlePages.toList());
        System.out.println(currentPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pagesQuantity", articlePages.getTotalPages()-1);

        if (auth != null) model.addAttribute("user", auth.getPrincipal());
        return "article/articlesAll";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable("id") Long id, Model model) {
        Optional<Article> article = articleService.findById(id);
        if (article.isEmpty()) return "error/404";
        model.addAttribute("article", article.get());
        model.addAttribute("comments",commentRepository.findCommentsByArticle_Id(id));
        return "article/article";
    }

}
