package ru.kan.otus.libcat.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.kan.otus.libcat.domain.Books;

public interface BooksRepository extends ReactiveMongoRepository<Books, String> {

   /* Mono<Void> delete(Books book);

    Mono<Books> findById(String id);

    Flux<Books> findByTitle(String title);

    Flux<Books> findAll();

    Mono<Books> save(Books books);*/
}
