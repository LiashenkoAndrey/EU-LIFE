package eulife.services;

import eulife.domain.News;
import eulife.repositories.NewsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private static final Logger logger = LogManager.getLogger(NewsService.class);

    private final NewsRepository newsRepository;


    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void save(News news) {
        logger.info("saving news" + news.getId());
        newsRepository.save(news);
    }

}
