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
import static org.mockito.Mockito.*;

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
    void checkIfCartDeleted() {
        Cart cart = new Cart();
        List <Cart> carts = new ArrayList<>();
        carts.add(cart);

       when(cartRepository.findAll()).thenReturn(new ArrayList<>());

        cartService.deleteCart(cart);

        verify(cartRepository, times(1)).delete(cart);
        assertTrue(cartRepository.findAll().isEmpty());
    }

    @Test
    void checkIfDeletedFromCart() {
        Cart cart = new Cart();
        Book book = new Book();
        cart.setId(1L);
        book.setId(1L);
        List<Book> books = new ArrayList<>();
        books.add(book);
        cart.setBooks(books);

        when(bookRepository.findById(1L)).thenReturn(book);
        when(cartRepository.findById(1L)).thenReturn(cart);

        cartService.deleteFromCart(cart.getId(), book.getId());

        assertTrue(cart.getBooks().isEmpty());
        assertTrue(cartRepository.findAll().isEmpty());
        verify(bookRepository, never()).delete(book);
    }

    @Test
    void checkIfBooksInCartDisplayed() {
        Cart cart = new Cart();
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        books.add(book);
        cart.setBooks(books);

        when(cartRepository.findById(1L)).thenReturn(cart);

        List<Book> booksResult = cartService.displayBooksInCart(1L);

        assertNotNull(booksResult);
        assertEquals(1, booksResult.size());
        assertEquals(book.getId(), booksResult.get(0).getId());
        assertEquals(book, booksResult.get(0));
    }
}