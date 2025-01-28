package com.application.bookstore.service;

import com.application.bookstore.model.Book;
import com.application.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepositoryMock;
    @InjectMocks
    BookService bookService;


    @Test
    void checkIfAllBooksAreFound() {
        //given
        List<Book> books = List.of(new Book(), new Book());
        when(bookRepositoryMock.findAll()).thenReturn(books);

        //when
        List<Book> result = bookService.findAll();

        //then
       assertEquals(books, result);
       assertThat(result).hasSize(2);
       assertNotNull(result);
       assertEquals(2, result.size());
    }

    @Test
    void checkIfFoundById() {

        //given
        Book book = new Book();
        when(bookRepositoryMock.findById(1L)).thenReturn(book);

        //when
        Book result = bookService.findById(1L);

        //then
        assertEquals(book, result);
        assertThat(result).isNotNull();
        assertThat(result).isSameAs(book);
    }

    @Test
    void checkIfAdded() {
        //given
        Book book = new Book();
        when(bookRepositoryMock.save(book)).thenReturn(book);

        //when
        bookService.add(book);

        //then
        verify(bookRepositoryMock).save(book);
        assertNotNull(book);

    }

    @Test
    void checkIfDeleted() {
        //given
        Book book = new Book();

        //when
        bookService.delete(book);

        //then
        verify(bookRepositoryMock).delete(book);
    }

    @Test
    void checkIfEdited() {
        //given
        Book book = new Book();

        when(bookRepositoryMock.save(book)).thenReturn(book);

        //when
        Book result = bookService.edit(book);

        //then
        assertEquals(book, result);

    }
}