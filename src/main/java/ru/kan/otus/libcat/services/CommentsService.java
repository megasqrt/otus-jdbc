package ru.kan.otus.libcat.services;

public interface CommentsService {

    void printAllByBookId(long bookid);

    void addComment(String bookName, String authorName, String genreName);

    void deleteAllCommentByBookid(Long bookId);


}
