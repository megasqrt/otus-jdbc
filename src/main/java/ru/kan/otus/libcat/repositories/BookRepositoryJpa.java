package ru.kan.otus.libcat.repositories;

import ru.kan.otus.libcat.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {

    Integer getCount();

    void deleteById(long id);

    void updateTitleById(long id, String title);

    Optional<Books> findById(long id);

    List<Books> findByTitle(String title);

    List<Books> findAll();

    Books save(Books books);
}
