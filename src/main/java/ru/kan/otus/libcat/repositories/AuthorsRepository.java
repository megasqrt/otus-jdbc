package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kan.otus.libcat.domain.Authors;

import java.util.Optional;

@RepositoryRestResource(path = "authors")
public interface AuthorsRepository extends JpaRepository<Authors, Long> {
    Optional<Authors> findByFullname(String fullname);
}
