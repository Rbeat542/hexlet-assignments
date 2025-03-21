package exercise.model;

import lombok.ToString;

@ToString
public final class Article {

    private Long id;

    @ToString.Include
    private String title;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;

    }

}
