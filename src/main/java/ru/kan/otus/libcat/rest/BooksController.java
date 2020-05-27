package ru.kan.otus.libcat.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.repositories.AuthorsRepository;
import ru.kan.otus.libcat.repositories.BooksRepository;
import ru.kan.otus.libcat.repositories.GenresRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BooksController {

    private final BooksRepository booksRepo;
    private final AuthorsRepository authorRepo;
    private final GenresRepository genresRepo;

    @GetMapping("/books")
    public String listPage(Model model) {
        List<Books> booksList = booksRepo.findAll();
        model.addAttribute("bookList", booksList);
        return "books";
    }

    @GetMapping("/books/add")
    public String showAddPage(Model model) {
        model.addAttribute("authorList", authorRepo.findAll());
        model.addAttribute("genreList", genresRepo.findAll());
        return "addBook";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Books book, Model model) {
        booksRepo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit")
    public String showEditPage(@RequestParam(value = "id", required = true) long id, Model model) {
        Optional<Books> book = booksRepo.findById(id);
        model.addAttribute("book", book.get());
        model.addAttribute("authorList", authorRepo.findAll());
        model.addAttribute("genreList", genresRepo.findAll());
        return "editBook";
    }

    @PostMapping("/books/edit")
    public String editBook(@RequestParam(value = "id", required = true) long id,
                           @ModelAttribute Books book, Model model) {
        booksRepo.save(book);
        return "redirect:/books";
    }

    @PostMapping("/books/delete")
    public String deletePost(@RequestParam(value = "id", required = true) long id,
                             @ModelAttribute Books book, Model model) {
        booksRepo.delete(book);
        return "redirect:/books";
    }
}
