package com.application.bookstore.service;

import com.application.bookstore.model.Cart;
import com.application.bookstore.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void checkIfAllCartsFound() {
        List<Cart> carts = List.of(new Cart(), new Cart(), new Cart());

        when(cartRepository.findAll()).thenReturn(carts);

        List<Cart> cartsFound = cartService.findAll();
        assertEquals(carts.size(), cartsFound.size());
        assertEquals(carts.get(0), cartsFound.get(0));
        assertThat(cartsFound).hasSize(3);
    }

    @Test
    void findById() {
    }

    @Test
    void findByCustomer() {
    }

    @Test
    void addToCart() {
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