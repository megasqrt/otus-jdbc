package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kan.otus.libcat.domain.Authors;

import java.util.Optional;

public interface AuthorsRepositoryJpa extends JpaRepository<Authors, Long> {

    Optional<Authors> findByFullName(String fullName);
}
