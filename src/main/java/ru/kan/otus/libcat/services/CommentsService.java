package ru.kan.otus.libcat.services;

public interface CommentsService {

    void printAllByBookId(long bookid);

    void deleteAllCommentByBookid(Long bookId);


}
