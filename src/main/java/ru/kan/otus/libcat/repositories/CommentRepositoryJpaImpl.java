package ru.kan.otus.libcat.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comments> listCommentsByBook(Books book) {
        TypedQuery<Comments> query = em.createQuery("select c from Comments c where c.book=:book", Comments.class)
                .setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public Comments addComment(Comments comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void delete(Comments comment) {
        em.remove(em.contains(comment) ? comment : em.merge(comment));
        em.flush();
    }

    @Override
    public Optional<Comments> findById(long id) {
        return Optional.ofNullable(em.find(Comments.class, id));
    }
}
