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

    public Cart findByCustomer(Customer customer) {
        Customer customerId = customerRepository.findById(customer.getId());
        return cartRepository.findByCustomer(customerId);
    }

    public Cart addToCart(Long bookId, Long customerId){
        log.info("Adding book to cart: CUSTOMER ID = {}, BOOK ID = {}", customerId, bookId);

        Book book = bookRepository.findById(bookId);
        if(book == null) {
            log.error("Book with ID {} not found", bookId);
            throw new RuntimeException("Book not found");
        }

        Customer customer = customerRepository.findById(customerId);
        if(customer == null) {
            log.error("Customer with ID {} not found", customerId);
            throw new RuntimeException("Customer not found");
        }

        Cart cart = cartRepository.findByCustomer(customer);
        if(cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart.getBooks().add(book);
            cart = cartRepository.save(cart);
            log.info("New cart created for the customer with ID: {}", customerId);
        }else{
        cart.getBooks().add(book);
        log.info("Book with ID: {} was added to the cart", bookId);
        return cartRepository.save(cart);
    }
    return cart;
    }

    public void deleteCart(Cart cart){
        log.info("Deleting cart: " + cart);
        cartRepository.delete(cart);
    }

    public void deleteFromCart(long cartId, long bookId){
        log.info("Deleting book with ID: " + bookId + " from cart: " +cartId);
        Cart cart = cartRepository.findById(cartId);
        if(cart == null) {
            log.error("Cart with ID {} not found", cartId);
            throw new RuntimeException("Cart with ID " + cartId + " not found");
        }
        Book book = bookRepository.findById(bookId);
        if(book == null) {
            log.error("Book with ID {} not found", bookId);
            throw new RuntimeException("Book with ID " + bookId + " not found");
        }
        List<Book> books = cart.getBooks();
        if(books.contains(book)) {
            books.remove(book);
            cartRepository.save(cart);
            log.info("Book with ID: {} was removed from the cart", bookId);
        }else{
            log.warn("Book with ID: {} was not found in the cart", bookId);
            throw new RuntimeException("Book with ID " + bookId + " was not found in the cart");
        }
    }


   public List<Book> displayBooksInCart(long cartId){
        log.info("Display all books in the cart");
       Cart cartById = cartRepository.findById(cartId);
       return cartById.getBooks();
   }
}
