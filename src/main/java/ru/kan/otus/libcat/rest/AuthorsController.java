package ru.kan.otus.libcat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.repositories.AuthorsRepository;

import java.util.List;

@RestController
public class AuthorsController {

    private final AuthorsRepository authorRepo;

    @Autowired
    public AuthorsController(AuthorsRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping("/api/authors")
    public List<Authors> listPage() {
        return authorRepo.findAll();
    }

    @PostMapping("/api/authors/add")
    public String addBook(@ModelAttribute Authors authors, Model model) {
        authorRepo.save(authors);
        return "added";
    }

    @DeleteMapping("/api/author/{id}")
    public String deleteCourse(@PathVariable String id) {
        authorRepo.deleteById(id);
        return "deleted";
    }
}
