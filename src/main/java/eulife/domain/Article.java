package eulife.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private String text;

    @ManyToOne
    private User author;

    public Long getId() {
        return id;
    }

    private CustomDate date_of_creation;

    public CustomDate getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(CustomDate date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
}
