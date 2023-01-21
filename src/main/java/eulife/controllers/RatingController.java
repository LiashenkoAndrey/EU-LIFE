package eulife.controllers;

import eulife.domain.User;
import eulife.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpResponse;

@Controller
@RequestMapping("/rating")
public class RatingController {


    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<org.springframework.http.HttpStatus> like(@RequestParam("comment_id") Long comment_id,
                               @RequestParam("isLike") boolean isLike,
                               Authentication auth) {
        ratingService.updateRatingOrSaveNew((User) auth.getPrincipal(), comment_id, isLike);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
