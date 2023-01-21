package eulife.repositories;


import eulife.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findRatingByUser_IdAndComment_Id(Long user_id, Long comment_id);
}
