package com.application.bookstore.controller;

import com.application.bookstore.model.Customer;
import com.application.bookstore.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RegistrationController {
    private final CustomerService customerService;

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration-form";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("customer") Customer customer, String confirmPassword, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        customerService.registerCustomer(customer, confirmPassword);
        return "redirect:/login";
    }
}

