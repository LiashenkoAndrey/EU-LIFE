package eulife.controllers;


import eulife.domain.Comment;
import eulife.domain.Rating;
import eulife.domain.User;
import eulife.repositories.CommentRepository;
import eulife.repositories.RatingRepository;
import eulife.repositories.UserRepository;
import eulife.services.RatingService;
import eulife.services.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RatingTest {

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

//    @Test
//    public void ratingTest() {
//        User user = userRepository.findById(13L).get();
//        ratingService.updateRatingOrSaveNew(user, 1L, false);
//
//    }
//
//    @After
//    public void deleteRating() {
//        Rating rating = ratingRepository.findRatingByUser_IdAndComment_Id(13L, 1L).get();
//        ratingRepository.delete(rating);
//    }

    @Test
    public void getLikes() {
        Comment comment = commentRepository.findById(8L).get();
        System.out.println(comment.getLikes());
        System.out.println(comment.getDislikes());
//        Integer likes = ratingRepository.getLikesByCommentId(8L);
//        System.out.println(likes);
    }


}
