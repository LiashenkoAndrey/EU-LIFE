package eulife.services;

import eulife.domain.News;
import eulife.domain.dto.NewsDto;
import eulife.repositories.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News save(News news) {
        News savedNews = newsRepository.save(news);
        return savedNews;
    }

    public List<NewsDto> findAllDto() {
        return newsRepository.findAllNewsWithLimitedText();
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }
}
