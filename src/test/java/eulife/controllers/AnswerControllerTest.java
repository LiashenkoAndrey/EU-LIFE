package eulife.controllers;


import eulife.domain.Answer;
import eulife.repositories.AnswerRepository;
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
public class AnswerControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void saveAnswerToDatabase() {
        Answer answer = new Answer();
        answer.setText("TEST");
        answer.setDate_of_creation(new Date());
        answer.setAuthor(userRepository.findById(25L).orElseThrow(EntityNotFoundException::new));
        answer.setQuestion(questionRepository.findById(10L).orElseThrow(EntityNotFoundException::new));

        answerRepository.save(answer);

        try {
            Answer expected = new Answer();
            expected.setText("TEST");
            Example<Answer> example = Example.of(expected);
            Assertions.assertFalse(answerRepository.findOne(example).isEmpty());
        } finally {
            answerRepository.delete(answer);
        }
    }
}
