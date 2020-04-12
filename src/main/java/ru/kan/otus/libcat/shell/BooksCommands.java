package ru.kan.otus.libcat.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kan.otus.libcat.dao.AuthorsDao;
import ru.kan.otus.libcat.dao.BooksDao;
import ru.kan.otus.libcat.dao.GenresDao;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Genres;

@ShellComponent
@RequiredArgsConstructor
public class BooksCommands {

    private final BooksDao booksDao;
    private final AuthorsDao authorsDao;
    private final GenresDao genresDao;

    @ShellMethod(value = "list all book", key = {"lb", "listBook"})
    public String listBooks() {
        booksDao.getAll().forEach(books -> System.out.println("id:" + books.getId() +
                " tile:" + books.getTitle() +
                " authorId:" + books.getAuthorId() +
                " genreId:" + books.getGenreId()));
        return "This is all books in catalog";
    }

    @ShellMethod(value = "insert new book in catalog", key = {"ib", "insertBook"})
    public String createBook(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        Authors foundAuthor = authorsDao.getByName(author);
        Genres foundGenre = genresDao.getByName(genre);
        if (foundAuthor == null)
            return "Not found author:" + author;
        if (foundGenre == null)
            return "Not found genre:" + genre;
        Books newBook = new Books(title, foundAuthor.getId(), foundGenre.getId());
        Long newId = booksDao.insert(newBook);
        return "Insert a new book " + title + " under id:" + newId;
    }

    @ShellMethod(value = "Find book in catalog by Id", key = {"fbi", "findBookById"})
    public String findBookByID(@ShellOption Long id) {
        Books books = booksDao.getById(id);
        if (books == null)
            return "Nothing found";
        return "Find a book " + books.getTitle() + " author:" + authorsDao.getById(books.getAuthorId()).getFullName() +
                " genre:" + genresDao.getById(books.getGenreId()).getTitle() +
                " by id:" + id;
    }


    @ShellMethod(value = "Find book in catalog by tile", key = {"fbt", "findBookByTitle"})
    public String findBookByTitle(@ShellOption String title) {
        Books books = booksDao.getByTitle(title);
        if (books == null)
            return "Nothing found";
        return "Find a book " + books.getTitle() + " by id:" + title;
    }

    @ShellMethod(value = "Delete book in catalog by Id", key = {"db", "deleteBook"})
    public String deleteBook(@ShellOption Long id) {
        int deletedRowCount = booksDao.deleteById(id);
        return "Delete a book " + deletedRowCount;
    }
}
