package eulife.services;

import eulife.domain.Comment;
import eulife.domain.Rating;
import eulife.domain.User;
import eulife.repositories.CommentRepository;
import eulife.repositories.RatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    private final CommentRepository commentRepository;

    public RatingService(RatingRepository ratingRepository, CommentRepository commentRepository) {
        this.ratingRepository = ratingRepository;
        this.commentRepository = commentRepository;
    }


    public void updateRatingOrSaveNew(User user, Long comment_id, boolean isLike) {
        Comment comment = commentRepository.findById(comment_id).orElseThrow(EntityNotFoundException::new);
        Optional<Rating> optionalRating = ratingRepository.findRatingByUser_IdAndComment_Id(user.getId(), comment_id);
        boolean ratingIsEmpty = optionalRating.isEmpty();
        if (ratingIsEmpty) saveRating(new Rating(user, comment, isLike));
        else {
            Rating rating = optionalRating.get();
            rating.setIs_like(isLike);
            saveRating(rating);
        }
    }

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

}
