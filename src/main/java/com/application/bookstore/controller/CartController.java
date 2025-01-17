package com.application.bookstore.controller;

import com.application.bookstore.model.Cart;
import com.application.bookstore.model.Customer;
import com.application.bookstore.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/display-carts")
    public String displayCarts(Model model) {
        List<Cart> carts = cartService.findAll();
        model.addAttribute("carts", carts);
        return "display-carts";
    }

    @GetMapping("/display-cart")
    public String displayCart(Customer customer, Model model) {
        cartService.findByCustomer(customer);
        return "display-cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(long bookId, long userId, Model model){
        Cart cart = cartService.addToCart(bookId, userId);
        model.addAttribute("cart", cart);
        return "redirect:/display-cart";
    }
}
