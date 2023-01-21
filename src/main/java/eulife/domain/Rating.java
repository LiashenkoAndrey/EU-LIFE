package eulife.domain;

import jakarta.persistence.*;

@Entity
public class Rating {

    public Rating(User user, Comment comment, boolean is_like) {
        this.comment = comment;
        this.user = user;
        this.is_like = is_like;
    }

    public Rating() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Comment comment;

    @OneToOne
    private User user;

    private boolean is_like;

    public boolean is_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }

    public Long getId() {
        return id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
