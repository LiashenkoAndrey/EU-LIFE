package eulife.services;

import eulife.domain.Question;
import eulife.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public void save(Question question) {
        questionRepository.save(question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
