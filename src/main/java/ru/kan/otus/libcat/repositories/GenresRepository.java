package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kan.otus.libcat.domain.Genres;

import java.util.Optional;

public interface GenresRepository extends JpaRepository<Genres, Long> {
    Optional<Genres> findByTitle(String title);

}
