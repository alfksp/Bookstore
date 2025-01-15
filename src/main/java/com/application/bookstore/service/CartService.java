package com.application.bookstore.service;

import com.application.bookstore.model.Book;
import com.application.bookstore.model.Cart;
import com.application.bookstore.model.Customer;
import com.application.bookstore.repository.BookRepository;
import com.application.bookstore.repository.CartRepository;
import com.application.bookstore.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    public CartService(CartRepository cartRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public List<Cart> findAll() {
        log.info("Display all carts");
        return cartRepository.findAll();
    }

    public Cart findById(int id) {
        log.info("Display cart by id: {}", id);
        return cartRepository.findById(id);
    }

    public Cart create(Cart cart) {
        log.info("Create new cart: {}", cart);
        return cartRepository.save(cart);
    }

    public Cart addToCart(long customerId, long bookId){
        log.info("Adding book to cart: CUSTOMER ID = {}, BOOK ID = {}", customerId, bookId);

        Customer customer = customerRepository.findById(customerId);
        if(customer == null) {
            log.error("Customer with ID {} not found", customerId);
        return null;
        }

        Book book = bookRepository.findById(bookId);
        if(book == null) {
            log.error("Book with ID {} not found", bookId);
        return null;
        }

        Cart cart = cartRepository.findByCustomer(customer);
        if(cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart = cartRepository.save(cart);
            log.info("New cart created for the customer with ID: {}", customerId);
        }

        cart.getBooks().add(book);
        log.info("Book with ID: {} was added to the cart", bookId);
        return cartRepository.save(cart);

    }

}
