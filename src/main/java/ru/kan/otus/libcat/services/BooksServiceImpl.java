package ru.kan.otus.libcat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.AuthorsRepositoryJpa;
import ru.kan.otus.libcat.repositories.BooksRepositoryJpa;
import ru.kan.otus.libcat.repositories.GenresRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableMongoRepositories
public class BooksServiceImpl implements BooksService {

    private static final String EXCEPTION_UPDATE_MESSAGE = "Не удалось обновить книгу";
    @Autowired
    private BooksRepositoryJpa bookRepo;
    private final AuthorsRepositoryJpa authorsRepo;
    private final GenresRepositoryJpa genresrsRepo;
    private final MessagePrinter printer;

    @Override
    public void printAll() {
        bookRepo.findAll();
        List<Books> BooksList = bookRepo.findAll();
        printer.printBooksHeader();
        BooksList.forEach(b -> printer.printBooksTable(b.getId(), b.getTitle(), b.getAuthor().getFullName(), b.getGenre().getTitle()));
    }

    @Override
    public void addBook(String bookTitle, String authorName, String genreTitle) {
        Authors author = authorsRepo.findByFullName(authorName).orElseGet(() -> authorsRepo.save(new Authors(authorName)));
        Genres genre = genresrsRepo.findByTitle(genreTitle).orElseGet(() -> genresrsRepo.save(new Genres(genreTitle)));
        List<Comments> comments = null;

        Books book = new Books("0", bookTitle, author, genre, comments);
        long newBookId = Long.parseLong(bookRepo.save(book).getId());
        printer.pm("Insert a new book " + bookTitle + " under id:" + newBookId);
    }

    @Override
    public void addAuthorToBook(long bookId, String authorName) {
        Books book = bookRepo.findById(bookId).get();
        Authors author = authorsRepo.findByFullName(authorName).get();
        book.setAuthor(author);
        bookRepo.save(book);
    }

    @Override
    public void addGenresToBook(long bookId, String genreTitle) {
        Books book = bookRepo.findById(bookId).get();
        Genres genres = genresrsRepo.findByTitle(genreTitle).get();
        book.setGenre(genres);
        bookRepo.save(book);
    }

    @Override
    public void updateBookName(long bookId, String newBookName) {
        try {
            Books book = bookRepo.findById(bookId).get();
            book.setTitle(newBookName);
            bookRepo.save(book);
        } catch (Exception ex) {
            System.out.println(EXCEPTION_UPDATE_MESSAGE);
        }
    }

    @Override
    public void updateBookAuthor(long bookId, String authorName) {
        try {
            Books book = bookRepo.findById(bookId).get();
            Authors author = authorsRepo.findByFullName(authorName).get();
            book.setAuthor(author);
            bookRepo.save(book);
        } catch (Exception ex) {
            System.out.println(EXCEPTION_UPDATE_MESSAGE);
        }
    }

    @Override
    public void updateBookGenre(long bookId, String genreTitle) {
        try {
            Books book = bookRepo.findById(bookId).get();
            Genres genre = genresrsRepo.findByTitle(genreTitle).get();
            book.setGenre(genre);
            bookRepo.save(book);
        } catch (Exception ex) {
            System.out.println(EXCEPTION_UPDATE_MESSAGE);
        }
    }

    @Override
    public void deleteBook(long bookId) {
        Optional<Books> books = bookRepo.findById(bookId);
        books.ifPresent(bookRepo::delete);
    }
}
