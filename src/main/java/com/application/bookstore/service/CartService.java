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

    public Cart findById(Long id) {
        log.info("Display cart by id: {}", id);
        return cartRepository.findById(id);
    }

    public void findByCustomer(Customer customer) {
        Customer customerId = customerRepository.findById(customer.getId());
        cartRepository.findByCustomer(customerId);
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

    public void deleteCart(Cart cart){
        log.info("Deleting cart: " + cart);
        cartRepository.delete(cart);
    }

    public void deleteFromCart(long cartId, Book book){
        log.info("Deleting book from cart: " + book);
        Cart cart = cartRepository.findById(cartId);
        cart.getBooks().remove(book);
    }


   public List<Book> displayBooksInCart(long cartId){
        log.info("Display all books in the cart");
       Cart cartById = cartRepository.findById(cartId);
       return cartById.getBooks();
   }
}
