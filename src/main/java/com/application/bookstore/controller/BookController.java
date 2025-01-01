package com.application.bookstore.controller;

import com.application.bookstore.model.Book;
import com.application.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/main")
    public String displayMainPage() {
        return "main";
    }


    @GetMapping("/display-books")
    public String displayAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "display-books";
    }

    @GetMapping("/display-book-form")
    public String displayBookForm(Model model){
        model.addAttribute("bookID", null);
        return "display-book-form";
    }

    @GetMapping("/display-book")
    public String displayBookById(@RequestParam long id, Model model){
        Book book = bookService.findById(id);
        if(book == null){
            model.addAttribute("error", "Book with given ID wasn't found");
            return "display-book-form";
        }
        model.addAttribute("book", book);
        return "display-book";
    }

    @GetMapping("/add-book-form")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        return "add-book-form";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes){
        try {
            bookService.add(book);
            redirectAttributes.addFlashAttribute("message", "Book was added");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Adding book error");
        }

        return "redirect:display-books";
    }

    @PostMapping("/delete-book")
    public String deleteBook(@RequestParam long id, RedirectAttributes redirectAttributes){
        Book book = bookService.findById(id);
        if(book != null){
            bookService.delete(book);
            redirectAttributes.addFlashAttribute("message", "Book was deleted");
        }else{
            redirectAttributes.addFlashAttribute("error", "Book wasn't found");
        }
        return "redirect:display-books";
    }

    @PostMapping("/edit-book")
    public String editBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes){
        try {
            bookService.edit(book);
            redirectAttributes.addFlashAttribute("message", "Book was edited");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Editing book error");
        }

        return "redirect:display-books";
    }
}

