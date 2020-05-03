package ru.kan.otus.libcat.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kan.otus.libcat.domain.Books;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BooksRepositoryJpaImpl implements BooksRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer getCount() {
        return findAll().size();
    }

    @Override
    public void delete(Books book) {
        em.remove(em.contains(book) ? book : em.merge(book));
        em.flush();
    }

    @Override
    public Optional<Books> findById(long bookId) {
        return Optional.ofNullable(em.find(Books.class, bookId));
    }

    @Override
    public List<Books> findByTitle(String bookTitle) {
        TypedQuery<Books> query = em.createQuery("select b from Books b where b.title=:title",
                Books.class);
        query.setParameter("title", bookTitle);
        return query.getResultList();
    }

    @Override
    public List<Books> findAll() {
        return em.createQuery("select b from Books b", Books.class).getResultList();
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
