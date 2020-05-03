package ru.kan.otus.libcat;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.BooksRepositoryJpa;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DisplayName("Репозиторий для работы с книгами должен ")
@DataJpaTest
class BooksJpaTest {

    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final String EXPECTED_BOOK_TITLE = "Война и мир";
    private static final String EXPECTED_BOOK_AUTHOR = "Толстой Лев Николаевич";
    private static final String EXPECTED_BOOK_GENRES = "Роман";
    private static final String NEW_BOOK_TITLE = "title";
    private static final long EXPECTED_BOOK_ID = 1;

    @Autowired
    private BooksRepositoryJpa bookRepo;

    @Autowired
    private TestEntityManager em;

    @DisplayName("удалять запись в БД")
    @Test
    void shouldDeleteBookById() {
        Books firstBook = em.find(Books.class, EXPECTED_BOOK_ID);
        assertThat(firstBook).isNotNull();

        bookRepo.delete(firstBook);

        val deletedBook = em.find(Books.class, EXPECTED_BOOK_ID);

        assertThat(deletedBook).isNull();
    }

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void shouldFindBookById() {
        val book = bookRepo.findById(EXPECTED_BOOK_ID);
        assertThat(book).isNotNull();
        assertThat(book.get().getTitle()).isEqualTo(EXPECTED_BOOK_TITLE);
    }

    @DisplayName("добавляет новые книги в каталог")
    @Test
    void shouldInsertBook() {
        Books newBook = new Books(0, NEW_BOOK_TITLE,
                new Authors(0, EXPECTED_BOOK_AUTHOR),
                new Genres(0, EXPECTED_BOOK_GENRES),
                new ArrayList<>());

        Books expectedID = bookRepo.save(newBook);
        Optional<Books> expectedBook = bookRepo.findById(expectedID.getId());

        assertThat(expectedBook.get().getTitle()).isEqualTo(NEW_BOOK_TITLE);
        assertThat(expectedBook.get()).isEqualToComparingFieldByField(newBook);
    }

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void shouldGetBookByTitle() {
        val book = bookRepo.findByTitle(EXPECTED_BOOK_TITLE);
        assertThat(book).isNotNull();
        assertThat(book.get(0).getId()).isEqualTo(EXPECTED_BOOK_ID);
    }

    @DisplayName("возвращает перечень всех книг")
    @Test
    void shouldGetAllBooks() {
        assertThat(bookRepo.findAll().size()).isEqualTo(EXPECTED_BOOK_COUNT);
    }
}