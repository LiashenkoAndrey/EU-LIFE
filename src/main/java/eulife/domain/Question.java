package eulife.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity
@Table(name = "questions")
public class Question {
    public Question() {
    }

    public Question(String text, User author, Date date_of_creation, String description) {
        this.text = text;
        this.author = author;
        this.date_of_creation = date_of_creation;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private User author;

    private Date date_of_creation;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }
}
