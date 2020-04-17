package ru.kan.otus.libcat.repositories;

import ru.kan.otus.libcat.domain.Comments;

import java.util.List;

public interface CommentRepositoryJpa {
    List<Comments> listCommentsByBookId(long bookId);

    Comments addComment(Comments comment);

    void deleteById(long Id);

}
