package com.application.bookstore.controller;

import com.application.bookstore.model.Customer;
import com.application.bookstore.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;


    public LoginController(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "login-form";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Customer customer = customerService.findByUsername(username);
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
        if (passwordEncoder.matches(password, customer.getPassword())) {
            session.setAttribute("customer", customer);
            return "redirect:/main";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid password");
            return "redirect:/login";
        }
    }

    @GetMapping("/login?error=true")
    public String showLoginErrorPage(Model model) {
        model.addAttribute("error", "Invalid username or password");
        return "login-form";
    }
}
