package ru.kan.otus.libcat.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kan.otus.libcat.domain.Books;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer getCount() {
        return findAll().size();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Books b where b.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateTitleById(long id, String title) {
        Query query = em.createQuery("update Books b " +
                "set b.title=:title " +
                "where b.id=:id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Books> findById(long id) {
        return Optional.ofNullable(em.find(Books.class, id));
    }

    @Override
    public List<Books> findByTitle(String title) {
        TypedQuery<Books> query = em.createQuery("select b from Books b where b.title=:title",
                Books.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Books> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("authorAndGenre-eg");
        TypedQuery<Books> query = em.createQuery("select b from Books b", Books.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Books save(Books book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }
}
