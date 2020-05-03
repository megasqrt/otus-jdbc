package ru.kan.otus.libcat.repositories;

import ru.kan.otus.libcat.domain.Genres;

import java.util.List;
import java.util.Optional;

public interface GenresRepositoryJpa {

    void delete(Genres genre);

    Optional<Genres> findById(long genreId);

    Optional<Genres> findByTitle(String title);

    List<Genres> findAll();

    Genres save(Genres Genres);
}
