package ru.kan.otus.libcat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.AuthorsRepository;
import ru.kan.otus.libcat.repositories.BooksRepository;
import ru.kan.otus.libcat.repositories.GenresRepository;
import ru.kan.otus.libcat.service.BooksServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("ACL должен")
public class SecurityTest {

    private final static int EXPECTED_BOOK_COUNT = 2;
    private final static int EXPECTED_BOOK_ORDER = 0;
    private static final String NEW_BOOK_TITLE = "TEST_TITLE";
    private static final long FIRST_ID_OBJECT = 1L;

    @Autowired
    private BooksServiceImpl bookService;
    @Autowired
    private BooksRepository booksRepo;
    @Autowired
    private AuthorsRepository authorsRepo;
    @Autowired
    private GenresRepository genresRepo;

    @Test
    @DisplayName("давать админам просматривать книги")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void findAllUsersUsingAdminTest() {
        assertThat(bookService.findAll()).hasSize(EXPECTED_BOOK_COUNT);
    }

    @Test
    @DisplayName("давать пользователям просматривать книги")
    @WithMockUser(username = "user", roles = "USER")
    public void userShouldBeReadBook() {
        assertThat(bookService.findAll()).hasSize(EXPECTED_BOOK_COUNT);
    }

    @Test
    @DisplayName("не давать пользователям редактировать книги")
    @WithMockUser(username = "user", roles = "USER")
    public void userNotSaved() {
        Books book = booksRepo.findById(FIRST_ID_OBJECT).orElse(null);
        book.setTitle("test1");
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            bookService.save(book);
        });
    }

    @Test
    @DisplayName("не давать пользователям создавать книги")
    @WithMockUser(username = "user", roles = "USER")
    public void userNotCreatedBook() {
        Authors createdAuthor = authorsRepo.findById(FIRST_ID_OBJECT).orElseThrow();
        Genres createdGenre = genresRepo.findById(FIRST_ID_OBJECT).orElseThrow();
        Books newBook = Books.builder().title(NEW_BOOK_TITLE).author(createdAuthor).genre(createdGenre).build();
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            bookService.save(newBook);
        });
    }

    @Test
    @DisplayName("давать админам создавать книги")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void adminShouldCreatedBook() {
        Authors createdAuthor = authorsRepo.findById(FIRST_ID_OBJECT).orElseThrow();
        Genres createdGenre = genresRepo.findById(FIRST_ID_OBJECT).orElseThrow();
        Books newBook = Books.builder().title(NEW_BOOK_TITLE).author(createdAuthor).genre(createdGenre).build();
        bookService.save(newBook);
        Assertions.assertEquals(booksRepo.findByTitle(NEW_BOOK_TITLE).get(EXPECTED_BOOK_ORDER).getTitle(), NEW_BOOK_TITLE);
    }

    @Test
    @DisplayName("давать админам создавать книги")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void adminShouldEditBook() {
        Books newBook = booksRepo.findById(FIRST_ID_OBJECT).orElseThrow();
        newBook.setTitle(NEW_BOOK_TITLE);
        bookService.save(newBook);
        Assertions.assertEquals(booksRepo.findByTitle(NEW_BOOK_TITLE).get(EXPECTED_BOOK_ORDER).getTitle(), NEW_BOOK_TITLE);
    }
}