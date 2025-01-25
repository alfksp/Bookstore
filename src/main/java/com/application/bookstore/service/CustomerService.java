package com.application.bookstore.service;

import com.application.bookstore.model.Customer;
import com.application.bookstore.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerService {
    private CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public void registerCustomer(Customer customer, String confirmPassword) {
if (customer.getPassword().equals(confirmPassword)) {
    throw new IllegalArgumentException("Passwords do not match");
}
         String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);

    }

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
