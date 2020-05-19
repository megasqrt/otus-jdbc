package ru.kan.otus.libcat;

import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.repositories.AuthorsRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan("ru.kan.otus.libcat.mongock")
@DisplayName("Репозиторий для работы с авторами должен ")
class AuthorsRepositoryTest {

    private static final int EXPECTED_AUTHOR_COUNT = 2;
    private static final String EXPECTED_AUTHOR_FULLNAME = "Толстой Лев Николаевич";
    private static final String EXPECTED_AUTHOR_ID = "1";
    private static final String NEW_AUTHOR_FULLNAME = "Пелевин";

    @Autowired
    private AuthorsRepository authorsRepo;

    @DisplayName("находить автора по его ID")
    @Test
    void findById() {
        val author = authorsRepo.findById(EXPECTED_AUTHOR_ID);
        assertThat(author).isNotNull();
        assertThat(author.get().getFullName()).isEqualTo(EXPECTED_AUTHOR_FULLNAME);
    }

    @DisplayName("находить автора по его FULLNAME")
    @Test
    void findByFullName() {
        val author = authorsRepo.findByFullName(EXPECTED_AUTHOR_FULLNAME);
        assertThat(author).isNotNull();
        assertThat(author.get().getId()).isEqualTo(EXPECTED_AUTHOR_ID);
    }

    @DisplayName("находить всех авторов")
    @Test
    void findAll() {
        assertThat(authorsRepo.findAll().size()).isEqualTo(EXPECTED_AUTHOR_COUNT);
    }

    @DisplayName("добавлять новый жанр в бд")
    @Test
    void save() {
        Authors newAuthor = new Authors(NEW_AUTHOR_FULLNAME);
        authorsRepo.save(newAuthor);
        Authors foundAuthor = authorsRepo.findByFullName(NEW_AUTHOR_FULLNAME).orElse(null);
        Assert.assertEquals(newAuthor, foundAuthor);
    }
}