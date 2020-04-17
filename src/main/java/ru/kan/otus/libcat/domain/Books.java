package ru.kan.otus.libcat.domain;

public class Books extends Entity {
    private final String title;
    private final Long author_id;
    private final Long genre_id;

    public Books(Long id, String title, Long author, Long genre) {
        super(id);
        this.title = title;
        this.author_id = author;
        this.genre_id = genre;
    }

    public Books(String booksTitle, Long author, Long genre) {
        super(null);
        this.title = booksTitle;
        this.author_id = author;
        this.genre_id = genre;
    }


    public String getTitle() {
        return title;
    }

    public Long getAuthorId() {
        return author_id;
    }

    public Long getGenreId() {
        return genre_id;
    }
}
