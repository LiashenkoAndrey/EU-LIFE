package eulife.services;

import eulife.domain.Article;
import eulife.repositories.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public Page<Article> findPage(Pageable pageable, Integer currentPage) {
        return articleRepository.findAll(pageable.withPage(currentPage));
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
