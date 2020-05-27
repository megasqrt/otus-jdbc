package ru.kan.otus.libcat.repository;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.AuthorsRepository;
import ru.kan.otus.libcat.repositories.BooksRepository;
import ru.kan.otus.libcat.repositories.GenresRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий для работы с книгами должен ")
class BooksRepositoryTest {

    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final String EXPECTED_BOOK_TITLE = "Война и мир";
    private static final String EXPECTED_BOOK_AUTHOR = "Толстой Лев Николаевич";
    private static final String EXPECTED_BOOK_GENRES = "Роман";
    private static final String NEW_BOOK_TITLE = "title";
    private static final long EXPECTED_BOOK_ID = 1;
    private static final long DELETED_BOOK_ID = 2;

    @Autowired
    private BooksRepository bookRepo;
    @Autowired
    private AuthorsRepository authorsRepo;
    @Autowired
    private GenresRepository genresRepo;

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void shouldFindBookById() {
        val book = bookRepo.findById(EXPECTED_BOOK_ID);
        assertThat(book.get()).isNotNull();
        assertThat(book.get().getTitle()).isEqualTo(EXPECTED_BOOK_TITLE);
    }

    @DisplayName("удалять запись в БД")
    @Test
    void shouldDeleteBookById() {
        val firstBook = bookRepo.findById(DELETED_BOOK_ID);
        assertThat(firstBook.get()).isNotNull();

        bookRepo.delete(firstBook.get());

        val deletedBook = bookRepo.findById(DELETED_BOOK_ID);

        Assertions.assertTrue(deletedBook.isEmpty());
    }


    @DisplayName("добавляет новые книги в каталог")
    @Test
    void shouldInsertBook() {

        Authors createdAuthor = authorsRepo.save(Authors.builder().fullname(EXPECTED_BOOK_AUTHOR).build());
        Genres createdGenre = genresRepo.save(Genres.builder().title(EXPECTED_BOOK_GENRES).build());
        Books newBook = Books.builder().title(NEW_BOOK_TITLE).author(createdAuthor).genre(createdGenre).build();

        Books expectedID = bookRepo.save(newBook);
        Optional<Books> expectedBook = bookRepo.findById(expectedID.getId());

        assertThat(expectedBook.get().getTitle()).isEqualTo(NEW_BOOK_TITLE);
        assertThat(expectedBook.get()).isEqualToComparingFieldByField(newBook);
    }

    @DisplayName("находить и возвращать книги по их названию")
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