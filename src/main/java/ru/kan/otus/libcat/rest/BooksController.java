package ru.kan.otus.libcat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;
import ru.kan.otus.libcat.repositories.AuthorsRepository;
import ru.kan.otus.libcat.repositories.BooksRepository;
import ru.kan.otus.libcat.repositories.CommentRepository;
import ru.kan.otus.libcat.repositories.GenresRepository;

import java.util.List;

@Controller
public class BooksController {

    private final BooksRepository booksRepo;
    private final AuthorsRepository authorRepo;
    private final GenresRepository genresRepo;
    private final CommentRepository commentRepo;


    @Autowired
    public BooksController(BooksRepository repository, AuthorsRepository authorRepo, GenresRepository genresRepo, CommentRepository commentRepo) {
        this.booksRepo = repository;
        this.authorRepo = authorRepo;
        this.genresRepo = genresRepo;
        this.commentRepo = commentRepo;
    }


    @GetMapping("/api/books")
    public Flux<Books> listPage() {
        System.out.println("1");
       /* List<Books> booksList = booksRepo.findAll();
        model.addAttribute("bookList", booksList);
        return "index";*/
        return booksRepo.findAll();
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        model.addAttribute("authorList", authorRepo.findAll());
        model.addAttribute("genreList", genresRepo.findAll());
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Books book, Model model) {
        booksRepo.save(book);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String showEditPage(@RequestParam(value = "id", required = true) String id, Model model) {
       /* Books book = booksRepo.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        model.addAttribute("authorList", authorRepo.findAll());
        model.addAttribute("genreList", genresRepo.findAll());*/
        return "editBook";
    }

    @PostMapping("/edit")
    public String editBook(@RequestParam(value = "id", required = true) String id,
                           @ModelAttribute Books book, Model model) {
        List<Comments> commentList = commentRepo.findAllCommentsByBook(book);
        book.setComment(commentList);
        booksRepo.save(book);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam(value = "id", required = true) String id,
                             @ModelAttribute Books book, Model model) {
        booksRepo.delete(book);
        return "redirect:/";
    }
}
