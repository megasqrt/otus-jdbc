package ru.kan.otus.libcat.services;

public interface BooksService {

    void printAll();

    void addBook(String bookName, String authorName, String genreName);

    void addAuthorToBook(String bookId, String authorName);

    void addGenresToBook(String bookId, String genreTitle);

    void updateBookName(String bookId, String newBookName);

    void updateBookAuthor(String bookId, String authorName);

    void updateBookGenre(String bookId, String genreTitle);

    void deleteBook(String bookId);
}
