package ru.kan.otus.tasker.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Репозиторий для работы с книгами должен ")
class TaskRepositoryTest {
/*
    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final String EXPECTED_BOOK_TITLE = "Война и мир";
    private static final String EXPECTED_BOOK_AUTHOR = "Толстой Лев Николаевич";
    private static final String EXPECTED_BOOK_GENRES = "Роман";
    private static final String NEW_BOOK_TITLE = "title";
    private static final long EXPECTED_BOOK_ID = 1;
    private static final long DELETED_BOOK_ID = 2;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository authorsRepo;
    @Autowired
    private GenresRepository genresRepo;

    @DisplayName("находить и возвращать книги по их id")
    @Test
    void shouldFindBookById() {
        val book = taskRepository.findById(EXPECTED_BOOK_ID);
        assertThat(book.get()).isNotNull();
        assertThat(book.get().getTitle()).isEqualTo(EXPECTED_BOOK_TITLE);
    }

    @DisplayName("удалять запись в БД")
    @Test
    void shouldDeleteBookById() {
        val firstBook = taskRepository.findById(DELETED_BOOK_ID);
        assertThat(firstBook.get()).isNotNull();

        taskRepository.delete(firstBook.get());

        val deletedBook = taskRepository.findById(DELETED_BOOK_ID);

        Assertions.assertTrue(deletedBook.isEmpty());
    }


    @DisplayName("добавляет новые книги в каталог")
    @Test
    void shouldInsertBook() {

        Position createdAuthor = authorsRepo.save(Position.builder().fullname(EXPECTED_BOOK_AUTHOR).build());
        Task createdGenre = genresRepo.save(Task.builder().title(EXPECTED_BOOK_GENRES).build());
        Task newBook = Task.builder().title(NEW_BOOK_TITLE).build();

        Task expectedID = taskRepository.save(newBook);
        Optional<Task> expectedBook = taskRepository.findById(expectedID.getId());

        assertThat(expectedBook.get().getTitle()).isEqualTo(NEW_BOOK_TITLE);
        assertThat(expectedBook.get()).isEqualToComparingFieldByField(newBook);
    }


    @DisplayName("возвращает перечень всех книг")
    @Test
    void shouldGetAllTask() {
        assertThat(taskRepository.findAll().size()).isEqualTo(EXPECTED_BOOK_COUNT);
    }*/
}