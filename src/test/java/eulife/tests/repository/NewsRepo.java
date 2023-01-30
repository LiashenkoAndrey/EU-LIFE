package eulife.tests.repository;

import eulife.domain.News;
import eulife.domain.dto.NewsDto;
import eulife.repositories.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepo {

    @Autowired
    NewsRepository newsRepository;

    @Test
    public void selectAllNewsWithLimitedText() {
        List<NewsDto> newsList = newsRepository.findAllNewsWithLimitedText();
        for (NewsDto n : newsList) System.out.println(n.getText());
    }
}
