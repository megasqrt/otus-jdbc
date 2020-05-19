package ru.kan.otus.libcat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kan.otus.libcat.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends MongoRepository<Books, String> {

    void delete(Books book);

    Optional<Books> findById(String id);

    List<Books> findByTitle(String title);

    List<Books> findAll();

    Books save(Books books);
}
