package ru.kan.otus.libcat;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.BookRepositoryJpaImpl;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должен ")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BooksJpaTest {

    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final String EXPECTED_BOOK_TITLE = "Война и мир";
    private static final String NEW_BOOK_TITLE = "title";
    private static final long EXPECTED_BOOK_ID = 1;

    @Autowired
    private BookRepositoryJpaImpl rjpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое кол-во книг в бд")
    @Test
    void getCount() {
        assertThat(rjpa.getCount()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("удалять запись в БД")
    @Test
    void deleteById() {
        val firstBook = em.find(Books.class, EXPECTED_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        rjpa.deleteById(EXPECTED_BOOK_ID);

        val deletedBook = em.find(Books.class, EXPECTED_BOOK_ID);

        assertThat(deletedBook).isNull();
    }

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void findById() {
        assertThat(rjpa.findById(EXPECTED_BOOK_ID)).isNotNull();
        assertThat(rjpa.findById(EXPECTED_BOOK_ID).get().getTitle()).isEqualTo(EXPECTED_BOOK_TITLE);
    }

    @DisplayName("добавляет новые книги в каталог")
    @Test
    void insert() {

        Books newBook = new Books(0, NEW_BOOK_TITLE,
                new Authors(0, "Толстой Лев Николаевич"),
                new Genres(0, "Роман"),
                new ArrayList<>());

        Books expectedID = rjpa.save(newBook);
        Optional<Books> expectedBook = rjpa.findById(expectedID.getId());

        assertThat(expectedBook.get().getTitle()).isEqualTo(NEW_BOOK_TITLE);
        assertThat(expectedBook.get()).isEqualToComparingFieldByField(newBook);
    }

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void getByTitle() {
        assertThat(rjpa.findByTitle("Война и мир")).isNotNull();
        assertThat(rjpa.findByTitle("Война и мир").get(0).getId()).isEqualTo(EXPECTED_BOOK_ID);
    }

    @DisplayName("возвращает перечень всех книг")
    @Test
    void getAll() {
        assertThat(rjpa.findAll().size()).isEqualTo(EXPECTED_BOOK_COUNT);
    }
}