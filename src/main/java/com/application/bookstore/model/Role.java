package com.application.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "ROLES")
@Data
public class Role {

    @Id
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Customer> customers;

}
