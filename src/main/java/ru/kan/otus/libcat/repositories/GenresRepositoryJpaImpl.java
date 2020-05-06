package ru.kan.otus.libcat.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Genres;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenresRepositoryJpaImpl implements GenresRepositoryJpa {

    private final EntityManager em;

    @Override
    public void delete(Genres genres) {
        em.remove(em.contains(genres) ? genres : em.merge(genres));
    }

    @Override
    public Optional<Genres> findById(long authorId) {
        return Optional.ofNullable(em.find(Genres.class, authorId));
    }

    @Override
    public Optional<Genres> findByTitle(String title) {
        TypedQuery<Genres> query = em.createQuery("select g from Genres g where g.title=:title",
                Genres.class);
        query.setParameter("title", title);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genres> findAll() {
        return em.createQuery("select g from Genres g", Genres.class).getResultList();
    }

    @Override
    public Genres save(Genres Genres) {
        if (Genres.getId() == 0) {
            em.persist(Genres);
            return Genres;
        } else {
            return em.merge(Genres);
        }
    }
}
