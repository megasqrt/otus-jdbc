package ru.kan.otus.libcat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kan.otus.libcat.domain.Authors;
import ru.kan.otus.libcat.repositories.AuthorsRepository;

@Controller
public class AuthorsController {

    private final AuthorsRepository authorRepo;

    @Autowired
    public AuthorsController(AuthorsRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping("/authors")
    public String showAuthors(Model model) {
        model.addAttribute("authors", authorRepo.findAll());
        return "authors";
    }

    @GetMapping("/authors/add")
    public String showAddPage(Model model) {
        return "addAuthor";
    }

    @PostMapping("/authors/add")
    public String addAuthors(@ModelAttribute Authors authors, Model model) {
        authorRepo.save(authors);
        return "redirect:/authors";
    }

    @GetMapping("/authors/edit")
    public String showEditPage(@RequestParam(value = "id", required = true) long id, Model model) {
        Authors author = authorRepo.getOne(id);
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/authors/edit")
    public String editAuthor(@RequestParam(value = "id", required = true) long id, @ModelAttribute Authors author) {
        authorRepo.save(author);
        return "redirect:/authors";
    }

    @DeleteMapping("/authors/delete{id}")
    public String deleteAuthors(@PathVariable Long id) {
        authorRepo.deleteById(id);
        return "redirect:/authors";
    }
}
