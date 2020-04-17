package ru.kan.otus.libcat;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;
import ru.kan.otus.libcat.repositories.CommentRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с комментариями должен ")
@DataJpaTest
@Import(CommentRepositoryJpaImpl.class)
class CommentsJpaTest {

    private static final long EXPECTED_BOOK_ID = 1;
    private static final long EXPECTED_COMMENT_ID = 1;
    private static final long EXPECTED_NEW_COMMENT_ID = 2;

    @Autowired
    private CommentRepositoryJpaImpl cJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("удалять комментарий БД")
    @Test
    void deleteById() {
        val firstComment = em.find(Comments.class, EXPECTED_COMMENT_ID);
        assertThat(firstComment).isNotNull();
        em.detach(firstComment);

        cJpa.deleteById(EXPECTED_COMMENT_ID);

        val deletedCommentarty = em.find(Comments.class, EXPECTED_COMMENT_ID);

        assertThat(deletedCommentarty).isNull();
    }

    @DisplayName("добавляет новые комментарии к книге")
    @Test
    void insert() {

        Comments newComment = new Comments(0, "test", new Books(EXPECTED_BOOK_ID, null, null, null, null));

        Long addCommentID = cJpa.addComment(newComment).getId();
        Long expectedCommentId = em.find(Comments.class, EXPECTED_NEW_COMMENT_ID).getId();

        assertThat(addCommentID).isEqualTo(expectedCommentId);
    }

    @DisplayName("возвращает все комментарии у книги")
    @Test
    void getAll() {
        assertThat(cJpa.listCommentsByBookId(EXPECTED_BOOK_ID)).isNotEmpty();
    }
}