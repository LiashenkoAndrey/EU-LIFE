package eulife.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "questions")
public class Question {
    public Question() {
    }

    public Question(String text, User author, Date date_of_creation) {
        this.text = text;
        this.author = author;
        this.date_of_creation = date_of_creation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @ManyToOne
    private User author;

    private Date date_of_creation;

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

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }
}
