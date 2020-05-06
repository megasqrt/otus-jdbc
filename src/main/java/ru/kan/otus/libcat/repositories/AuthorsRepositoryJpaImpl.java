package ru.kan.otus.libcat.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.kan.otus.libcat.domain.Authors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorsRepositoryJpaImpl implements AuthorsRepositoryJpa {

    private final EntityManager em;

    @Override
    public void delete(Authors authors) {
        em.remove(em.contains(authors) ? authors : em.merge(authors));
    }

    @Override
    public Optional<Authors> findById(long authorId) {
        return Optional.ofNullable(em.find(Authors.class, authorId));
    }

    @Override
    public Optional<Authors> findByFullName(String fullName) {
        TypedQuery<Authors> query = em.createQuery("select a from Authors a where a.fullName=:fullName",
                Authors.class);
        query.setParameter("fullName", fullName);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Authors> findAll() {
        return em.createQuery("select a from Authors a", Authors.class).getResultList();
    }

    @Override
    public Authors save(Authors authors) {
        if (authors.getId() == 0) {
            em.persist(authors);
            return authors;
        } else {
            return em.merge(authors);
        }
    }
}
