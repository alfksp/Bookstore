package com.application.bookstore.controller;

import com.application.bookstore.model.Customer;
import com.application.bookstore.service.CustomerService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;

    public RegistrationController(CustomerService customerService, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping
    public String registerCustomer(@ModelAttribute("customer") Customer customer, String confirmPassword, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }try {
            if(!customer.getPassword().equals(confirmPassword)) {
                model.addAttribute("error", "Passwords do not match");
                return "register";
            }
            customerService.registerCustomer(customer, confirmPassword);
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword())
            ));
            model.addAttribute("success", "Registration successfull");
            return "redirect:/login";
        }catch (Exception e){
            model.addAttribute("error", "Error during registration");
            return "register";
        }
    }
}
