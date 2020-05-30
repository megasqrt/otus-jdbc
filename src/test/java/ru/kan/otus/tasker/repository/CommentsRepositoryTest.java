package ru.kan.otus.tasker.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;


@DataMongoTest
@ComponentScan("ru.kan.otus.libcat.mongock")
@DisplayName("Репозиторий для работы с комментариями должен ")
class CommentsRepositoryTest {

  /*  private static final String EXPECTED_BOOK_ID = "1";
    private static final String EXPECTED_COMMENT_ID = "1";

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private TaskRepository taskRepo;

    @DisplayName("удалять комментарий БД")
    @Test
    void deleteById() {
        val firstComment = commentRepo.findById(EXPECTED_COMMENT_ID);
        assertThat(firstComment).isNotNull();

        commentRepo.delete(firstComment.get());

        val deletedCommentarty = commentRepo.findById(EXPECTED_COMMENT_ID);
        Assertions.assertTrue(deletedCommentarty.isEmpty());
    }

    @DisplayName("добавляет новые комментарии к книге")
    @Test
    void insert() {
        Optional<Task> book = taskRepo.findById(EXPECTED_BOOK_ID);
        Comments newComment = new Comments("0", "test", book.get());
        int firstCommentCount = commentRepo.findAllCommentsByBook(book.get()).size();

        commentRepo.save(newComment);

        int newCommentCount = commentRepo.findAllCommentsByBook(book.get()).size();

        assertThat(firstCommentCount).isLessThan(newCommentCount);
    }

    @DisplayName("возвращает все комментарии у книги")
    @Test
    void getAll() {
        Optional<Task> book = taskRepo.findById(EXPECTED_BOOK_ID);
        List<Comments> commentsList = commentRepo.findAllCommentsByBook(book.get());
        assertThat(commentsList).isNotEmpty();
        Assertions.assertEquals(2, commentsList.size());
    }*/
}