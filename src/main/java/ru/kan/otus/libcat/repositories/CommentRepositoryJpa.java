package ru.kan.otus.libcat.repositories;

import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    List<Comments> listCommentsByBook(Books book);

    Comments addComment(Comments comment);

    void delete(Comments comment);

    Optional<Comments> findById(long id);

}
