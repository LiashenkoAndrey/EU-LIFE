package eulife.tests.domain;


import eulife.domain.CustomDate;
import eulife.domain.News;
import eulife.domain.User;
import eulife.repositories.NewsRepository;
import eulife.repositories.UserRepository;
import eulife.services.UserService;
import eulife.tests.UserUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsTest {

    @Autowired
    UserService userService;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveNews() {
        User author = UserUtils.getDefaultUser();
        User savedAuthor = userService.saveNewUser(author);
        News news = new News();
        news.setAuthor(savedAuthor);
        news.setDescription("Hello world!");
        news.setText("Second news!");
        news.setDate_of_creation(new CustomDate());
        News savedNews = newsRepository.save(news);

        newsRepository.delete(savedNews);
        userRepository.delete(savedAuthor);
    }

}
