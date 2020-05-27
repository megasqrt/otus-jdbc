package ru.kan.otus.libcat.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kan.otus.libcat.domain.Genres;
import ru.kan.otus.libcat.repositories.GenresRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GenresController {

    private final GenresRepository genresRepo;

    @GetMapping("/api/genres")
    public List<Genres> listBoks() {
        return genresRepo.findAll();
    }

    @GetMapping("/api/genres/{id}")
    public Optional<Genres> findById(@PathVariable(name = "id") long id) {
        return genresRepo.findById(id);
    }

    @PostMapping(value = "/api/genres", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Genres addGenres(@RequestBody Genres book) {
        return genresRepo.save(book);
    }

    @DeleteMapping("/api/genres/{id}")
    public void deleteGenres(@PathVariable(name = "id") long id) {
        genresRepo.deleteById(id);
    }
}
