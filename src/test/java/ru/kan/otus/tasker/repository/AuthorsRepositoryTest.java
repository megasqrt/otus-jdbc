package ru.kan.otus.tasker.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Репозиторий для работы с авторами должен ")
class AuthorsRepositoryTest {
/*
    private static final int EXPECTED_AUTHOR_COUNT = 2;
    private static final String EXPECTED_AUTHOR_FULLNAME = "Толстой Лев Николаевич";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final String NEW_AUTHOR_FULLNAME = "Пелевин";

    @Autowired
    private EmployeeRepository authorsRepo;

    @DisplayName("находить автора по его ID")
    @Test
    void findById() {
        val author = authorsRepo.findById(EXPECTED_AUTHOR_ID);
        assertThat(author).isNotNull();
        assertThat(author.get().getFullname()).isEqualTo(EXPECTED_AUTHOR_FULLNAME);
    }

    @DisplayName("находить автора по его FULLNAME")
    @Test
    void findByFullName() {
        val author = authorsRepo.findByFullname(EXPECTED_AUTHOR_FULLNAME);
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
        Position newAuthor = new Position(NEW_AUTHOR_FULLNAME);
        authorsRepo.save(newAuthor);
        Position foundAuthor = authorsRepo.findByFullname(NEW_AUTHOR_FULLNAME).orElse(null);
        Assert.assertEquals(newAuthor, foundAuthor);
    }*/
}