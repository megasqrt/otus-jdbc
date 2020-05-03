package ru.kan.otus.libcat.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.repositories.BooksRepositoryJpa;
import ru.kan.otus.libcat.services.BooksService;
import ru.kan.otus.libcat.services.MessagePrinter;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class BooksCommands {

    private final BooksRepositoryJpa booksRepository;
    private final BooksService booksService;
    private final MessagePrinter printer;

    @ShellMethod(value = "list all book", key = {"lb", "listBook"})
    public void listBooks() {
        booksService.printAll();
    }

    @ShellMethod(value = "add new book in catalog", key = {"adb", "addBook"})
    public void addBook(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        booksService.addBook(title, author, genre);
    }

    @ShellMethod(value = "Find book in catalog by Id", key = {"fbi", "findBookById"})
    public String findBookByID(@ShellOption long id) {
        Optional<Books> findBook = booksRepository.findById(id);
        if (findBook.isPresent()) {
            return "Find a book " + findBook.get().getTitle() + " author:" + findBook.get().getAuthor().getFullName() +
                    " genre:" + findBook.get().getGenre().getTitle() +
                    " by id:" + id;
        } else {
            return "Nothing found";
        }
    }

    @ShellMethod(value = "Find book in catalog by tile", key = {"fbt", "findBookByTitle"})
    public String findBookByTitle(@ShellOption String title) {
        List<Books> findBooks = booksRepository.findByTitle(title);
        if (findBooks.isEmpty())
            return "Nothing found";
        StringBuilder message = new StringBuilder();
        findBooks.forEach(book -> message.append(book.getTitle()));
        return "Find a books " + message.toString() + " by title:" + title;
    }

    @ShellMethod(value = "Update book title by Id", key = {"ub", "updateBook"})
    public String updateBookTitle(@ShellOption long id, @ShellOption String title) {
        booksService.updateBookName(id, title);
        return "Book title updated";
    }

    @ShellMethod(value = "Delete book in catalog by Id", key = {"db", "deleteBook"})
    public String deleteBook(@ShellOption long id) {
        booksService.deleteBook(id);
        return "Book deleted";
    }

    @ShellMethod(value = "Add Author to book ", key = {"adab", "addAuthorToBook"})
    public String addAuthorToBook(@ShellOption long id, String fullName) {
        booksService.addAuthorToBook(id, fullName);
        return "Author added";
    }

    @ShellMethod(value = "Add Genre to book ", key = {"adgb", "addGenreToBook"})
    public String addGenrerToBook(@ShellOption long id, String title) {
        booksService.addGenresToBook(id, title);
        return "Genre added";
    }
}
