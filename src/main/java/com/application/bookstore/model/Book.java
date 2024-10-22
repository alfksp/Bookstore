package com.application.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BOOKS")
@Data
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "YEAR")
    private int year;
    @Column(name = "PRICE")
    private double price = 0.25;
    @Column(name = "BOOKS_AVAILABLE")
    private int booksAvailable;
}
