package ru.kan.otus.libcat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kan.otus.libcat.domain.Genres;

import java.util.List;
import java.util.Optional;

public interface GenresRepositoryJpa extends MongoRepository<Genres, Long> {

    void delete(Genres genre);

    Optional<Genres> findById(long genreId);

    Optional<Genres> findByTitle(String title);

    List<Genres> findAll();

    Genres save(Genres Genres);
}
