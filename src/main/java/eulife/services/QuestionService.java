package eulife.services;

import eulife.domain.Question;
import eulife.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public void save(Question question) {
        questionRepository.save(question);
    }

    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Page<Question> findAll(Pageable pageable, Integer currentPage) {
        return questionRepository.findAll(pageable.withPage(currentPage));
    }
}
