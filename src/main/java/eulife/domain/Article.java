package eulife.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
}
