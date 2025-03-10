package com.application.bookstore.controller;

import com.application.bookstore.model.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class MainController {
//    @GetMapping("/main")
//    public String displayMainPage(HttpSession session, Model model) {
//        Customer customer = (Customer) session.getAttribute("customer");
//        if (customer == null) {
//            System.out.println("No customer found in session. Redirecting to login.");
//            return "redirect:/login";
//        }
//        System.out.println("Customer found in session: " + customer.getUsername());
//        model.addAttribute("displayCartLink", "/display-cart");
//        return "main";
//    }

    @GetMapping("/main")
    public String displayMainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
            model.addAttribute("displayCartLink", "/display-cart");
            return "main";
        }
        return "redirect:/login";
    }
}
