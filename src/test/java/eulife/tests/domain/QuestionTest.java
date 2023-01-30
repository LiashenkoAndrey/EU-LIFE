package eulife.tests.domain;

import eulife.domain.CustomDate;
import eulife.domain.Question;
import eulife.domain.User;
import eulife.repositories.QuestionRepository;
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
public class QuestionTest {

    @Autowired
    UserService userService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    public void saveQuestion() {
        User savedAuthor = userService.saveNewUser(UserUtils.getDefaultUser());

        Question question = new Question();
        question.setAuthor(savedAuthor);
        question.setText("test");
        question.setDescription("test");
        question.setDate_of_creation(new CustomDate());
        Question savedQuestion = questionRepository.save(question);

        questionRepository.delete(savedQuestion);
        userRepository.delete(savedAuthor);
    }
}
