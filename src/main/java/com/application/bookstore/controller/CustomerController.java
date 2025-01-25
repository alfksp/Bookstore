package com.application.bookstore.controller;

import com.application.bookstore.model.Customer;
import com.application.bookstore.service.AuthenticationService;
import com.application.bookstore.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public CustomerController(CustomerService customerService, AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/main")
    public String displayMainPage(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }
        model.addAttribute("displayCartLink", "/display-cart");
        return "main";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = customerService.findByUsername(username);
        if (customer ==null){
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
        if(passwordEncoder.matches(password, customer.getPassword())){
            session.setAttribute("customer", customer);
            return "redirect:/main";
        }else {
            redirectAttributes.addFlashAttribute("error", "Invalid password");
            return "redirect:/login";
        }
    }
}
