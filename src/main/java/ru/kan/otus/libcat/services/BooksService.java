package ru.kan.otus.libcat.services;

public interface BooksService {

    void printAll();

    void addBook(String bookName, String authorName, String genreName);

    void addAuthorToBook(long bookId, String authorName);

    void addGenresToBook(long bookId, String genreTitle);

    void updateBookName(long bookId, String newBookName);

    void updateBookAuthor(long bookId, String authorName);

    void updateBookGenre(long bookId, String genreTitle);

    void deleteBook(long bookId);
}
