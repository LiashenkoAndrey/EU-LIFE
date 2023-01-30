package eulife.tests.domain;


import eulife.domain.Article;
import eulife.domain.CustomDate;
import eulife.domain.User;
import eulife.repositories.ArticleRepository;
import eulife.repositories.UserRepository;
import eulife.services.UserService;
import eulife.tests.UserUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void saveArticle() {
        User savedAuthor = userService.saveNewUser(UserUtils.getDefaultUser());

        Article article = new Article();
        article.setAuthor(savedAuthor);
        article.setDate_of_creation(new CustomDate());
        article.setTopic("Test");
        article.setText("test");

        Article savedArticle = articleRepository.save(article);
        articleRepository.delete(savedArticle);
        userRepository.delete(savedAuthor);
    }
}
