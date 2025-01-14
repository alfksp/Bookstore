package com.application.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CART")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Customer customer;
    @Column(name = "BOOK")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> books;
}
