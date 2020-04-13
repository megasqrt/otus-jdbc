package ru.kan.otus.libcat.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.BookRepositoryJpa;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class BooksCommands {

    private final BookRepositoryJpa rJpa;

    @ShellMethod(value = "list all book", key = {"lb", "listBook"})
    public String listBooks() {


        rJpa.findAll().forEach(books -> System.out.println("id:" + books.getId() +
                " tile:" + books.getTitle() +
                " author:" + books.getAuthor().getFullName() +
                " genre:" + books.getGenre().getTitle()));
        return "This is all books in catalog";
    }

    @ShellMethod(value = "insert new book in catalog", key = {"ib", "insertBook"})
    public String createBook(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        Authors createAuthor = new Authors(0, author);
        Genres createGenres = new Genres(0, genre);
        List<Comments> comments = null;

        Books newBook = new Books(0, title, createAuthor, createGenres, comments);

        return "Insert a new book " + title + " under id:" + rJpa.save(newBook).getId();
    }

    @ShellMethod(value = "Find book in catalog by Id", key = {"fbi", "findBookById"})
    public String findBookByID(@ShellOption long id) {
        Optional<Books> findBook = rJpa.findById(id);
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
        List<Books> findBooks = rJpa.findByTitle(title);
        if (findBooks.isEmpty())
            return "Nothing found";
        StringBuilder message = new StringBuilder();
        findBooks.forEach(book -> message.append(book.getTitle()));
        return "Find a books " + message.toString() + " by title:" + title;
    }

    @ShellMethod(value = "Delete book in catalog by Id", key = {"db", "deleteBook"})
    public String deleteBook(@ShellOption long id) {
        rJpa.deleteById(id);
        return "Book deleted";
    }
}
