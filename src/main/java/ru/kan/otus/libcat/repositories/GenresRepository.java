package ru.kan.otus.libcat.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.kan.otus.libcat.domain.Genres;

public interface GenresRepository extends ReactiveMongoRepository<Genres, String> {
}
