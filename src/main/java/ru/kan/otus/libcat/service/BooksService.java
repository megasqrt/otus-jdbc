package ru.kan.otus.libcat.service;

import ru.kan.otus.libcat.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    Books createBook(Books book);

    Books save(Books book);

    void delete(Long bookId);

    Optional<Books> findById(Long bookId);

    List<Books> findAll();

    void addAclEntries(Books book);
}
