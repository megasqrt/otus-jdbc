package ru.kan.otus.libcat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kan.otus.libcat.domain.Authors;

import java.util.List;
import java.util.Optional;

public interface AuthorsRepository extends MongoRepository<Authors, String> {

    void delete(Authors authors);

    Optional<Authors> findById(String authorId);

    Optional<Authors> findByFullName(String fullName);

    List<Authors> findAll();

    Authors save(Authors authors);
}
