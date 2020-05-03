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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с комментариями должен ")
@DataJpaTest
@Import(CommentRepositoryJpaImpl.class)
class CommentsJpaTest {

    private static final long EXPECTED_BOOK_ID = 1;
    private static final long EXPECTED_COMMENT_ID = 1;

    @Autowired
    private CommentRepositoryJpaImpl commentRepo;

    @Autowired
    private TestEntityManager em;

    @DisplayName("удалять комментарий БД")
    @Test
    void deleteById() {
        val firstComment = em.find(Comments.class, EXPECTED_COMMENT_ID);
        assertThat(firstComment).isNotNull();

        commentRepo.delete(firstComment);

        val deletedCommentarty = em.find(Comments.class, EXPECTED_COMMENT_ID);
        assertThat(deletedCommentarty).isNull();
    }

    @DisplayName("добавляет новые комментарии к книге")
    @Test
    void insert() {
        Books book = em.find(Books.class, EXPECTED_BOOK_ID);
        Comments newComment = new Comments(0, "test", book);
        int firstCommentCount = commentRepo.listCommentsByBook(book).size();

        commentRepo.addComment(newComment);
        int newCommentCount = commentRepo.listCommentsByBook(book).size();

        assertThat(firstCommentCount).isLessThan(newCommentCount);
    }

    @DisplayName("возвращает все комментарии у книги")
    @Test
    void getAll() {
        Books book = em.find(Books.class, EXPECTED_BOOK_ID);
        List<Comments> commentsList = commentRepo.listCommentsByBook(book);
        assertThat(commentsList).isNotEmpty();
        assertThat(commentsList.size()).isEqualTo(2);
    }
}