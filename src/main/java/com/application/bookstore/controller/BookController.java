package com.application.bookstore.controller;

import com.application.bookstore.model.Book;
import com.application.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/display-books")
    public String displayAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "display-books";
    }

    @GetMapping("/display-book/{id}")
    public String displayBookById(@PathVariable long id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "display-book";
    }
}

