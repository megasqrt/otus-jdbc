package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kan.otus.libcat.domain.Genres;

import java.util.Optional;

@RepositoryRestResource(path = "genres")
public interface GenresRepository extends JpaRepository<Genres, Long> {
    Optional<Genres> findByTitle(String title);
}
