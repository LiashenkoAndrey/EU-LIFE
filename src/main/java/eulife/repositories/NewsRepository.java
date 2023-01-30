
package eulife.repositories;

import eulife.domain.News;
import eulife.domain.dto.NewsDto;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = " select new eulife.domain.dto.NewsDto(n.id, left(n.description, 24), left(n.text, 125), n.date_of_creation) from News n")
    List<NewsDto> findAllNewsWithLimitedText();

}
