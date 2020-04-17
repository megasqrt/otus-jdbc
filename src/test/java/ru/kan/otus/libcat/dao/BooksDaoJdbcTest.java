package ru.kan.otus.libcat.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.kan.otus.libcat.domain.Books;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с книгами должно ")
@JdbcTest
@Import(BooksDaoJdbc.class)
class BooksDaoJdbcTest {

    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final int EXPECTED_BOOK_COUNT_AFTER_DELETE = 1;
    private static final String EXPECTED_BOOK_TITLE = "Война и мир";
    private static final String NEW_BOOK_TITLE = "title";
    private static final long EXPECTED_BOOK_ID = 1L;
    @Autowired
    private BooksDaoJdbc jdbc;

    @DisplayName("возвращать ожидаемое кол-во книг в бд")
    @Test
    void getCount() {
        assertThat(jdbc.getCount()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("удалять запись в БД")
    @Test
    void deleteById() {
        assertThat(jdbc.getCount()).isEqualTo(EXPECTED_BOOK_COUNT);
        assertThat(jdbc.getById(1L)).isNotNull();
        jdbc.deleteById(1L);
        assertThat(jdbc.getCount()).isEqualTo(EXPECTED_BOOK_COUNT_AFTER_DELETE);
        assertThat(jdbc.getById(1L)).isNull();
    }

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void getById() {
        assertThat(jdbc.getById(1L)).isNotNull();
        assertThat(jdbc.getById(1L).getTitle()).isEqualTo(EXPECTED_BOOK_TITLE);
    }

    @DisplayName("добавляет новые книги в каталог")
    @Test
    void insert() {
        Books newBook = new Books(3L, NEW_BOOK_TITLE, 2L, 2L);

        Long expectedID = jdbc.insert(newBook);
        Books expectedBook = jdbc.getById(expectedID);

        assertThat(expectedBook.getTitle()).isEqualTo(NEW_BOOK_TITLE);
        assertThat(expectedBook).isEqualToComparingFieldByField(newBook);
    }

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void getByTitle() {
        assertThat(jdbc.getByTitle("Война и мир")).isNotNull();
        assertThat(jdbc.getByTitle("Война и мир").getId()).isEqualTo(EXPECTED_BOOK_ID);
    }

    @DisplayName("возвращает перечень всех книг")
    @Test
    void getAll() {
        assertThat(jdbc.getAll().size()).isEqualTo(EXPECTED_BOOK_COUNT);
    }
}