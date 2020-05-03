package ru.kan.otus.libcat.repositories;

import ru.kan.otus.libcat.domain.Authors;

import java.util.List;
import java.util.Optional;

public interface AuthorsRepositoryJpa {

    void delete(Authors authors);

    Optional<Authors> findById(long authorId);

    Optional<Authors> findByFullName(String fullName);

    List<Authors> findAll();

    Authors save(Authors authors);
}
