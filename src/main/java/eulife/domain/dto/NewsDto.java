package eulife.domain.dto;

import eulife.domain.CustomDate;

public class NewsDto {

    private Long id;
    private String description;
    private String text;
    private CustomDate date_of_creation;


    public NewsDto(Long id, String description, String text, CustomDate date_of_creation) {
        this.id = id;
        this.description = description;
        this.text = text;
        this.date_of_creation = date_of_creation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
