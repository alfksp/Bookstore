package com.application.bookstore.service;

import com.application.bookstore.model.Customer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final CustomerService customerService;
    public CustomUserDetailsService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                        .username(customer.getUsername())
                                .password(customer.getPassword())
                                        .roles("USER_ROLE")
                                                .build();

    }
}
