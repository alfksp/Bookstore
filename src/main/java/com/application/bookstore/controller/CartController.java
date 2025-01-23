package com.application.bookstore.controller;

import com.application.bookstore.model.Book;
import com.application.bookstore.model.Cart;
import com.application.bookstore.model.Customer;
import com.application.bookstore.service.BookService;
import com.application.bookstore.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.application.bookstore.constants.CartControllerConstants.*;

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
        return DISPLAY_CARTS;
    }

    @GetMapping("/display-cart")
    public String displayCart(Customer customer,  Model model) {
        cartService.findByCustomer(customer);
        return DISPLAY_CART;
    }

    @PostMapping("/add-to-cart")
    public String addToCart(long bookId, long userId, Model model){
        Cart cart = cartService.addToCart(bookId, userId);
        model.addAttribute("cart", cart);
        return "redirect:/" + DISPLAY_CART;
    }

    @PostMapping("/delete-from-cart")
    public String deleteFromCart(@RequestParam Book book, @RequestParam long cartId, RedirectAttributes redirectAttributes){
        try{
            cartService.deleteFromCart(cartId, book);
            redirectAttributes.addFlashAttribute(SUCCESS, BOOK_DELETED_FROM_CART);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute(ERROR, BOOKS_NOT_FOUND);
        }
        return DISPLAY_CART;
    }

    @PostMapping("/delete-cart")
    public String deleteCart(@RequestParam long cartId, RedirectAttributes redirectAttributes){
        Cart cart = cartService.findById(cartId);
        if (cart != null) {
            cartService.deleteCart(cart);
            redirectAttributes.addFlashAttribute(SUCCESS, CART_DELETED);
        } else {
            redirectAttributes.addFlashAttribute(ERROR, CART_NOT_FOUND);
        }
        return "redirect:/" + DISPLAY_CARTS;
    }
}
