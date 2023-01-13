package eulife.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Entity
@Component
@Table(name = "comments")
public class Comment {

    public Comment(String text, User author, Answer answer, Date date_of_creation) {
        this.text = text;
        this.author = author;
        this.answer = answer;
        this.date_of_creation = date_of_creation;
    }

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    @Nullable
    private Answer answer;

    @ManyToOne
    @Nullable
    private Comment comment;

    @ManyToOne
    @Nullable
    private Article article;

    @OneToMany(mappedBy = "comment")
    @Nullable
    private List<Comment> commentList;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    private Date date_of_creation;

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
