package ru.kan.otus.libcat.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kan.otus.libcat.domain.Comments;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comments> listCommentsByBookId(long bookId) {
        //EntityGraph<?> entityGraph=em.getEntityGraph("authorAndGenre-eg");
        TypedQuery<Comments> query = em.createQuery("select c from Comments c", Comments.class);
        //query.setHint("javax.persistence.fetchgraph",entityGraph);
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
    public void deleteById(long Id) {
        Query query = em.createQuery("delete from Comments c where c.id=:id");
        query.setParameter("id", Id);
        query.executeUpdate();
    }
}
