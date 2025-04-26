package com.application.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "BOOKS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE")
    @NotEmpty(message = "Title required")
    private String title;
    @Column(name = "AUTHOR")
    @NotEmpty(message = "Author required")
    private String author;
    @Column(name = "YEAR")
    @NotNull(message = "Year required")
    private int year;
    @Column(name = "PRICE")
    @NotNull(message = "Price required")
    private double price;
    @Column(name = "BOOKSAVAILABLE")
    @NotNull(message = "Price required")
    private int booksAvailable;
    @ManyToMany(mappedBy = "books")
    private List<Cart> carts;
    @Column(name = "RETURNED")
    public boolean isReturned;
}
