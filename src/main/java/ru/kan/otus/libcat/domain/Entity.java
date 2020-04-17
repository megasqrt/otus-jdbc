package ru.kan.otus.libcat.domain;

public class Entity<T> {
    private final Long id;

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
