package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kan.otus.libcat.domain.Authors;

import java.util.List;
import java.util.Optional;

public interface AuthorsRepositoryJpa extends JpaRepository<Authors, Long> {

    void delete(Authors authors);

    Optional<Authors> findById(long authorId);

    Optional<Authors> findByFullName(String fullName);

    List<Authors> findAll();

    Authors save(Authors authors);
}
