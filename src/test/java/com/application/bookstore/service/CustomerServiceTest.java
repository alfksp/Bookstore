package com.application.bookstore.service;

import com.application.bookstore.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void getCustomerById() {
    }

    @Test
    void checkIfCustomerRegistered() {
        Customer customer = new Customer();
        customer.setName("a");
        customer.setSurname("a");
        customer.setEmail("a");
        customer.setUsername("a");
        customer.setPassword("b");
        customer.setConfirmPassword("b");

        customerService.registerCustomer(customer, customer.getConfirmPassword());
        Customer saved = customerService.findByUsername("a");
        assertNotNull(saved);
    }

    @Test
    void findByUsername() {
    }
}