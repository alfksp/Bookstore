package com.application.bookstore.repository;

import com.application.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void delete(Book book);

    List<Book> findByAuthor(List<Book> books, String author);
}

