package ru.kan.otus.libcat.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.GenresRepository;

@RestController
@RequiredArgsConstructor
public class GenresController {

    private final GenresRepository genresRepo;

    @GetMapping("/api/genres")
    public Flux<Genres> listBoks() {
        return genresRepo.findAll();
    }

    @GetMapping("/api/genres/{id}")
    public Mono<Genres> findById(@PathVariable(name = "id") String id) {
        return genresRepo.findById(id);
    }

    @PostMapping(value = "/api/genres", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Genres> addGenres(@RequestBody Genres book) {
        return genresRepo.save(book);
    }

    @DeleteMapping("/api/genres/{id}")
    public Mono<Void> deleteGenres(@PathVariable(name = "id") String id) {
        return genresRepo.deleteById(id);
    }
}
