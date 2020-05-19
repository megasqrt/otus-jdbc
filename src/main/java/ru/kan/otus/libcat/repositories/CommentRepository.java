package ru.kan.otus.libcat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comments, String> {
    List<Comments> findAllCommentsByBook(Books book);

    void delete(Comments comment);

    Optional<Comments> findById(String id);

}
