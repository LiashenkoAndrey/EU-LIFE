package eulife;

import eulife.domain.Question;
import eulife.repositories.QuestionRepository;
import eulife.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionUserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Test
    public void saveQuestionToDatabase() {
        Question expected = new Question();
        expected.setAuthor(userRepository.findById(25L).orElseThrow(EntityNotFoundException::new));
        expected.setDate_of_creation(new Date());
        expected.setDescription("TEST");
        expected.setText("TEST");

        questionRepository.save(expected);
        try {
            Question question = new Question();
            question.setText("TEST");
            Example<Question> example = Example.of(question);
            Assertions.assertFalse(questionRepository.findOne(example).isEmpty());
        } finally {
            questionRepository.delete(expected);
        }

    }
}
