package ru.kan.otus.libcat.domain;

public class Books extends Entity {
    private final String title;
    private final Long author;
    private final Long genre;

    public Books(Long id, String title, Long author, Long genre) {
        super(id);
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Books(String booksTitle, Long author, Long genre) {
        super(null);
        this.title = booksTitle;
        this.author = author;
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }

    public Long getAuthorId() {
        return author;
    }

    public Long getGenreId() {
        return genre;
    }
}
