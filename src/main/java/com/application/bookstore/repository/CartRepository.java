package com.application.bookstore.repository;

import com.application.bookstore.model.Cart;
import com.application.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAll();
    Cart findById(int id);
    Cart findByCustomer(Customer customer);
}
