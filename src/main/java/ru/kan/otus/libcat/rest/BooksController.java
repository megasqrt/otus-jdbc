package ru.kan.otus.libcat.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.repositories.AuthorsRepository;
import ru.kan.otus.libcat.repositories.BooksRepository;
import ru.kan.otus.libcat.repositories.GenresRepository;


@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BooksRepository booksRepo;
    private final AuthorsRepository authorRepo;
    private final GenresRepository genresRepo;


    @GetMapping("/api/books")
    public Flux<Books> listBoks() {
        return booksRepo.findAll();
    }

    @GetMapping("/api/books/{id}")
    public Mono<Books> findById(@PathVariable(name = "id") String id) {
        return booksRepo.findById(id);
    }

    @PostMapping(value = "/api/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Books> addBooks(@RequestBody Books book) {
        return booksRepo.save(book);
    }

    @DeleteMapping("/api/books/{id}")
    public Mono<Void> deleteBooks(@PathVariable(name = "id") String id) {
        return booksRepo.deleteById(id);
    }
}
