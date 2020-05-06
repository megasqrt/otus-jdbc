package ru.kan.otus.libcat.repositories;

import ru.kan.otus.libcat.domain.Comments;

import java.util.Optional;

public interface CommentRepositoryJpa {

    void delete(Comments comment);

    Optional<Comments> findById(long id);

}
