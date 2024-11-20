package com.application.bookstore.service;

import com.application.bookstore.model.Book;
import com.application.bookstore.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        log.info("Display all books");
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        log.info("Display book with id: " + id);
        return bookRepository.findById(id);
    }

    public void add(Book book) {
        log.info("Adding book: " + book);
        bookRepository.save(book);
    }

    public void delete(Book book) {
        log.info("Deleting book: " + book);
        bookRepository.delete(book);
    }

    public Book edit(Book book) {
        log.info("Updating book: " + book.getId());
        return bookRepository.save(book);
    }
}
