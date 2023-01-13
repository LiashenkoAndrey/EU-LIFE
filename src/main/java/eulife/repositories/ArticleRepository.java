package eulife.repositories;

import eulife.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Answer, Long> {
}
