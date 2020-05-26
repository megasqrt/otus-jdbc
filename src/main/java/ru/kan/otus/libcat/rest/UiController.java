package ru.kan.otus.libcat.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UiController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/addBook")
    public String addBook() {
        return "addBook";
    }

    @GetMapping("/editBook")
    public String editBook() {
        return "editBook";
    }

    @GetMapping("/authors")
    public String authors() {
        return "authors";
    }
}
