package eulife.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class News {

    public News() {
    }

    public News(Long id, String text, CustomDate date_of_creation, User author, String description) {
        this.id = id;
        this.text = text;
        this.date_of_creation = date_of_creation;
        this.author = author;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private CustomDate date_of_creation;

    @ManyToOne
    private User author;

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

    public CustomDate getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(CustomDate date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
