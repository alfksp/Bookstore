package com.application.bookstore.service;

import com.application.bookstore.model.Book;
import com.application.bookstore.model.Cart;
import com.application.bookstore.model.Customer;
import com.application.bookstore.repository.BookRepository;
import com.application.bookstore.repository.CartRepository;
import com.application.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void checkIfAllCartsFound() {
        List<Cart> carts = List.of(new Cart(), new Cart(), new Cart());

        when(cartRepository.findAll()).thenReturn(carts);

        List<Cart> cartsFound = cartService.findAll();

        assertThat(cartsFound).isEqualTo(carts);
        assertEquals(carts.size(), cartsFound.size());
        assertEquals(carts.get(0), cartsFound.get(0));
        assertThat(cartsFound).hasSize(3);
    }

    @Test
    void checkIfFoundById() {
        Cart cart = new Cart();
        when(cartRepository.findById(1L)).thenReturn(cart);

        Cart cartFound = cartService.findById(1L);

        assertNotNull(cartFound);
        assertEquals(cart, cartFound);
        assertEquals(cart.getId(), cartFound.getId());
    }

    @Test
    void checkIfFoundByCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        Cart cart = new Cart();
        cart.setCustomer(customer);

        when(cartRepository.findByCustomer(customer)).thenReturn(cart);
        when(customerRepository.findById(1L)).thenReturn(customer);

        Cart cartFound = cartService.findByCustomer(customer);

        assertNotNull(cartFound);
        assertEquals(cart, cartFound);
    }

    @Test
    void checkIfAddedToCart() {
        Cart cart = new Cart();
        Customer customer = new Customer();

        customer.setId(1L);

        Book book = new Book();
        book.setId(1L);

      List<Book> books = new ArrayList<>();
      books.add(book);

        cart.setCustomer(customer);
        cart.setBooks(books);

        when(cartRepository.save(cart)).thenReturn(cart);
        when(bookRepository.findById(1L)).thenReturn(book);
        when(customerRepository.findById(1L)).thenReturn(customer);

        Cart savedCart = cartService.addToCart(customer.getId(), book.getId());

        assertNotNull(savedCart);
        assertEquals(cart.getCustomer().getId(), savedCart.getCustomer().getId());
        assertEquals(1, savedCart.getBooks().size());
        assertEquals(book.getId(), savedCart.getBooks().get(0).getId());
    }

    @Test
    void deleteCart() {

    }

    @Test
    void deleteFromCart() {
    }

    @Test
    void displayBooksInCart() {
    }
}