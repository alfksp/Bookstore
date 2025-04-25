package com.application.bookstore.controller;

import com.application.bookstore.model.Book;
import com.application.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.application.bookstore.constants.BookControllerConstants.*;

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

    @GetMapping("/display-book-form")
    public String displayBookForm(Model model) {
        model.addAttribute("bookID", null);
        return "display-book-form";
    }

    @GetMapping("/display-book")
    public String displayBookById(@RequestParam long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            model.addAttribute(ERROR, BOOK_NOT_FOUND);
            return "display-book-form";
        }
        model.addAttribute("book", book);
        return "display-book";
    }

    @GetMapping("/add-book-form")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book-form";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(ERROR, ALL_FIELDS_REQUIRED);
            return "add-book-form";
        }
        try {
            bookService.add(book);
            redirectAttributes.addFlashAttribute(SUCCESS, BOOK_ADDED);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR, BOOK_NOT_ADDED);
        }

        return REDIRECT_DISPLAY_BOOKS;
    }

    @PostMapping("/delete-book")
    public String deleteBook(@RequestParam long id, RedirectAttributes redirectAttributes) {
        Book book = bookService.findById(id);
        if (book != null) {
            bookService.delete(book);
            redirectAttributes.addFlashAttribute(SUCCESS, BOOK_DELETED);
        } else {
            redirectAttributes.addFlashAttribute(ERROR, BOOK_NOT_FOUND);
        }
        return REDIRECT_DISPLAY_BOOKS;
    }

    @PostMapping("/edit-book")
    public String editBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        try {
            bookService.edit(book);
            redirectAttributes.addFlashAttribute(SUCCESS, BOOK_EDITED);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR, BOOK_NOT_EDITED);
        }

        return REDIRECT_DISPLAY_BOOKS;
    }

    @GetMapping("/display-books-by-author-form")
    public String displayBooksByAuthorForm(Model model){
        model.addAttribute("author", null);
        return "display-books-by-author-form";
    }

    @GetMapping("/display-books-by-author")
    public String displayBooksByAuthor(@RequestParam String author, Model model) {
        List<Book> booksByAuthor = bookService.findByAuthor(author);
        if (booksByAuthor.isEmpty()) {
            model.addAttribute(ERROR, "There are no books of this author in our bookstore");
            return "display-books-by-author-form";
        }
        model.addAttribute("booksByAuthor", booksByAuthor);
        return "display-books-by-author";
    }

}

