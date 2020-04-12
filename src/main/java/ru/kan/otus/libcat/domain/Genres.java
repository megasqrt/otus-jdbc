package ru.kan.otus.libcat.domain;

public class Genres extends Entity {

    private final String title;

    public Genres(Long id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
