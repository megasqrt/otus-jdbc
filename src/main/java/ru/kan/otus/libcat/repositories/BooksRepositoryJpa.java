package ru.kan.otus.libcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kan.otus.libcat.domain.Books;

import java.util.List;

public interface BooksRepositoryJpa extends JpaRepository<Books, Long> {

    List<Books> findByTitle(String title);
}
