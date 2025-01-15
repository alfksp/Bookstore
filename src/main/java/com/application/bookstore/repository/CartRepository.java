package com.application.bookstore.repository;

import com.application.bookstore.model.Book;
import com.application.bookstore.model.Cart;
import com.application.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    public List<Cart> findAll();
    public Cart findById(int id);
    public Cart findByCustomer(Customer customer);
}
