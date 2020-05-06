package ru.kan.otus.libcat.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Comments;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    private final EntityManager em;

    @Override
    public void delete(Comments comment) {
        em.remove(em.contains(comment) ? comment : em.merge(comment));
    }

    @Override
    public Optional<Comments> findById(long id) {
        return Optional.ofNullable(em.find(Comments.class, id));
    }
}
