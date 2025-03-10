package com.application.bookstore.controller;

import com.application.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void displayAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/display-books")
                        .with(user("username").password("password").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("display-books"));
    }

    @Test
    void displayBookForm() {
    }

    @Test
    void displayBookById() {
    }

    @Test
    void addBookForm() {
    }

    @Test
    void addBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void editBook() {
    }

}