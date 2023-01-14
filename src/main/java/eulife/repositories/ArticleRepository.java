package eulife.repositories;

import eulife.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

//    Page<ArticleTopicAndTextOnly> findAll(Pageable pageable);
}
