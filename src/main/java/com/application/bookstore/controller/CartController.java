package com.application.bookstore.controller;

import com.application.bookstore.model.Book;
import com.application.bookstore.model.Cart;
import com.application.bookstore.model.Customer;
import com.application.bookstore.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.application.bookstore.constants.CartControllerConstants.*;

@Controller
//@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(Customer customer, HttpSession session) {
//        if(session.getAttribute("customer") != null){
//            return "redirect:/display-cart";
//        }
//        session.setAttribute("customer", customer);
//        return "redirect:/display-cart";
//    }

    @GetMapping("/display-cart")
    public String displayCart(@SessionAttribute("customer") Customer customer, Model model){
     if(customer == null){
         return "redirect:/login";
     }
     Cart cart = cartService.findByCustomer(customer);
     if(cart == null){
         model.addAttribute(ERROR, "Cart not found for this customer");
         return "redirect:/display-carts";
     }
     model.addAttribute("cart", cart);
     return "display-cart";
    }

//    @GetMapping("/view-cart")
//    public String viewCart(@SessionAttribute("customer") Customer customer, Model model){
//        if(customer == null){
//            return "redirect:/login";
//        }
//        Cart cart = cartService.findByCustomer(customer);
//        model.addAttribute("cart", cart);
//        return "cart";
//    }

    @GetMapping("/display-carts")
    public String displayCarts(Model model) {
        List<Cart> carts = cartService.findAll();
        model.addAttribute("carts", carts);
        return DISPLAY_CARTS;
    }

//    @GetMapping("/add-to-cart-form")
//    public String addToCartForm(@SessionAttribute("customer") Customer customer, Model model) {
//        if(customer == null){
//            return "redirect:/login";
//        }
//        model.addAttribute("book", new Book());
//        return "add-to-cart-form";
//    }

    @PostMapping("/add-to-cart")
    public String addToCart(Long bookId, Long customerId, RedirectAttributes redirectAttributes) {
        try {
            cartService.addToCart(bookId, customerId);
            redirectAttributes.addFlashAttribute(SUCCESS, BOOK_ADDED_TO_CART);
            return "redirect:/display-cart";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR, BOOK_NOT_ADDED_TO_CART);
            return "redirect:/display-carts";
        }
    }


    @PostMapping("/delete-from-cart")
    public String deleteFromCart(@RequestParam("cartId") Long cartId, @RequestParam("bookId") Long bookId, RedirectAttributes redirectAttributes){
        try{
            cartService.deleteFromCart(cartId, bookId);
            redirectAttributes.addFlashAttribute(SUCCESS, BOOK_DELETED_FROM_CART);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute(ERROR, BOOKS_NOT_FOUND);
        }
        return "redirect:/display-cart";
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
