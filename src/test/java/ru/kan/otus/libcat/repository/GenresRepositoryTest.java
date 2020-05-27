package ru.kan.otus.libcat.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.GenresRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan("ru.kan.otus.libcat.mongock")
@DisplayName("Репозиторий для работы с жанрами должен ")
class GenresRepositoryTest {

    private static final int EXPECTED_GENRE_COUNT = 2;
    private static final String EXPECTED_GENRE_TITLE = "Роман";
    private static final long EXPECTED_GENRE_ID = 1;
    private static final String NEW_GENRE_TITLE = "Фентези";

    @Autowired
    private GenresRepository genreRepo;

    @DisplayName("находить жанр по его ID")
    @Test
    void shouldFindGenreById() {
        genreRepo.findAll().forEach(System.out::println);
        Optional<Genres> genre = genreRepo.findById(EXPECTED_GENRE_ID);
        assertThat(genre.get()).isNotNull();
        assertThat(genre.get().getTitle()).isEqualTo(EXPECTED_GENRE_TITLE);
    }

    @DisplayName("находить жанр по его FULLNAME")
    @Test
    void shouldFindGenreByFullName() {
        Optional<Genres> genre = genreRepo.findByTitle(EXPECTED_GENRE_TITLE);
        assertThat(genre.get()).isNotNull();
        assertThat(genre.get().getId()).isEqualTo(EXPECTED_GENRE_ID);
    }

    @DisplayName("находить все жанры")
    @Test
    void shouldFindAllGenres() {
        Assertions.assertEquals(EXPECTED_GENRE_COUNT, genreRepo.findAll().size());
    }

    @DisplayName("добавлять новый жанр в бд")
    @Test
    void shouldSaveNewGenre() {
        Genres newGenre = new Genres(NEW_GENRE_TITLE);
        genreRepo.save(newGenre);
        Optional<Genres> foundGenre = genreRepo.findByTitle(NEW_GENRE_TITLE);
        Assert.assertEquals(newGenre, foundGenre.get());
    }
}