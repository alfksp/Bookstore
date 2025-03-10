package com.application.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Transient
    private String confirmPassword;
    @Column(name = "USERNAME")
    private String username;
    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;
    @ManyToMany
    @JoinTable(
            name = "customers_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name")
    )
    private List<Role> roles;
}
