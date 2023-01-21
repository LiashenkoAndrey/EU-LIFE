package eulife.controllers;

import eulife.domain.Comment;
import eulife.domain.Rating;
import eulife.repositories.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void testComment() {
        Comment comment = commentRepository.findById(8L).get();
        System.out.println(comment.getAuthor().getId());
        List<Rating> ratingList = comment.getRatingList();
        for (Rating r : ratingList) System.out.println(r.getId());
    }
}
