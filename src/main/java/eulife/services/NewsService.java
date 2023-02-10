package eulife.services;

import eulife.domain.News;
import eulife.domain.dto.NewsDto;
import eulife.repositories.NewsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PersistenceContext
    EntityManager entityManager;

    public List<NewsDto> findLimitedList(int limit) {
        return entityManager.createQuery(" select new eulife.domain.dto.NewsDto(n.id, left(n.description, 24), left(n.text, 125), n.date_of_creation) from News n", NewsDto.class).setMaxResults(limit).getResultList();
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public Page<News> findPage(Pageable pageable, Integer currentPage) {
        return newsRepository.findAll(pageable.withPage(currentPage));
    }

    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }
}
