package ru.kan.otus.libcat.services;

public interface CommentsService {

    void printAllByBookId(String bookid);

    void deleteAllCommentByBookid(String bookId);

    void addComment(String bookId, String text);
}
