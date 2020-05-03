package ru.kan.otus.libcat;

import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.GenresRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с авторами должен ")
@DataJpaTest
@Import(GenresRepositoryJpaImpl.class)
class GenresRepositoryJpaTest {

    private static final int EXPECTED_GENRE_COUNT = 2;
    private static final String EXPECTED_GENRE_TITLE = "Роман";
    private static final long EXPECTED_GENRE_ID = 1;
    private static final String NEW_GENRE_TITLE = "Фентези";

    @Autowired
    private GenresRepositoryJpaImpl GenresRepo;

    @Autowired
    private TestEntityManager em;

    @DisplayName("находить жанр по его ID")
    @Test
    void shouldFindGenreById() {
        val author = GenresRepo.findById(EXPECTED_GENRE_ID);
        assertThat(author).isNotNull();
        assertThat(author.get().getTitle()).isEqualTo(EXPECTED_GENRE_TITLE);
    }

    @DisplayName("находить жанр по его FULLNAME")
    @Test
    void shouldFindGenreByFullName() {
        val author = GenresRepo.findByTitle(EXPECTED_GENRE_TITLE);
        assertThat(author).isNotNull();
        assertThat(author.get().getId()).isEqualTo(EXPECTED_GENRE_ID);
    }

    @DisplayName("находить все жанры")
    @Test
    void shouldFindAllGenres() {
        assertThat(GenresRepo.findAll().size()).isEqualTo(EXPECTED_GENRE_COUNT);
    }

    @DisplayName("добавлять новый жанр в бд")
    @Test
    void shouldSaveNewGenre() {
        Genres newAuthor = new Genres(NEW_GENRE_TITLE);
        GenresRepo.save(newAuthor);
        Genres foundAuthor = GenresRepo.findByTitle(NEW_GENRE_TITLE).orElse(null);
        Assert.assertEquals(newAuthor, foundAuthor);
    }
}