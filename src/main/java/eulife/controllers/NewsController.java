package eulife.controllers;

import eulife.domain.CustomDate;
import eulife.domain.News;
import eulife.domain.Question;
import eulife.domain.User;
import eulife.services.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/news")
public class NewsController {


    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String addNews(Model model, Authentication auth) {
        model.addAttribute("user", auth.getPrincipal());
        model.addAttribute("news",new News());
        return "news/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new")
    public String saveNews(@ModelAttribute("news") News news, Authentication auth) {
        news.setAuthor((User) auth.getPrincipal());
        news.setDate_of_creation(new CustomDate());
        newsService.save(news);
        return "redirect:/";
    }


    @GetMapping("/all")
    public String showAllNews(@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                              @PageableDefault(size = 25) Pageable pageable,
                              Model model, Authentication auth) {

        // pagination
        currentPage -= 1;
        Page<News> pages = newsService.findPage(pageable, currentPage);
        model.addAttribute("news", pages.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pagesQuantity", pages.getTotalPages()-1);

        if (auth != null) model.addAttribute("user", auth.getPrincipal());
        return "news/newsAll";
    }

    @GetMapping("/{id}")
    public String getNews(@PathVariable Long id, Model model, Authentication auth) {
        Optional<News> news = newsService.findById(id);
        if (news.isEmpty()) return "error/404";
        if (auth != null) model.addAttribute("user", (User) auth.getPrincipal());
        model.addAttribute("news", news.get());
        return "news/news";
    }
}
