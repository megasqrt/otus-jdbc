package ru.kan.otus.libcat.dao;

import ru.kan.otus.libcat.domain.Books;

import java.util.List;

public interface BooksDao {
    Integer getCount();

    int deleteById(Long id);

    Books getById(Long id);

    Books getByTitle(String title);

    Long insert(Books books);

    List<Books> getAll();

}
