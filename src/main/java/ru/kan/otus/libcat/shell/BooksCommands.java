package ru.kan.otus.libcat.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.repositories.BooksRepository;
import ru.kan.otus.libcat.services.BooksService;
import ru.kan.otus.libcat.services.MessagePrinter;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class BooksCommands {

    private final BooksRepository booksRepository;
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
    public String findBookByID(@ShellOption String id) {
        Optional<Books> findBook = booksRepository.findById(id);
        return findBook.map(books -> "Find a book " + books.getTitle() + " author:" + books.getAuthor().getFullName() +
                " genre:" + books.getGenre().getTitle() +
                " by id:" + id).orElse("Nothing found");
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
    public String updateBookTitle(@ShellOption String id, @ShellOption String title) {
        booksService.updateBookName(id, title);
        return "Book title updated";
    }

    @ShellMethod(value = "Delete book in catalog by Id", key = {"db", "deleteBook"})
    public String deleteBook(@ShellOption String id) {
        booksService.deleteBook(id);
        return "Book deleted";
    }

    @ShellMethod(value = "Add Author to book ", key = {"adab", "addAuthorToBook"})
    public String addAuthorToBook(@ShellOption String id, String fullName) {
        booksService.addAuthorToBook(id, fullName);
        return "Author added";
    }

    @ShellMethod(value = "Add Genre to book ", key = {"adgb", "addGenreToBook"})
    public String addGenrerToBook(@ShellOption String id, String title) {
        booksService.addGenresToBook(id, title);
        return "Genre added";
    }
}
