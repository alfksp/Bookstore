package com.application.bookstore.service;

import com.application.bookstore.model.Customer;
import com.application.bookstore.repository.BookRepository;
import com.application.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
            void setUp() {
        customer = new Customer();
        customer.setName("a");
        customer.setSurname("a");
        customer.setEmail("a");
        customer.setUsername("a");
        customer.setPassword("b");
        customer.setConfirmPassword("b");
    }

    @Test
    void getCustomerById() {

        when(customerRepository.findById(1L)).thenReturn(customer);

        Customer customerById = customerService.getCustomerById(1);

        assertNotNull(customerById);
        assertEquals(customer, customerById);
    }

    @Test
    void checkIfCustomerRegistered() {

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer saved = customerService.registerCustomer(customer, customer.getConfirmPassword());

        assertNotNull(saved);
        assertEquals("a", saved.getName());
        assertEquals("b", saved.getConfirmPassword());
    }

    @Test
    void ckeckIfFoundByUsername() {
        when(customerRepository.findByUsername(customer.getUsername())).thenReturn(customer);

        Customer saved = customerService.findByUsername("a");

        assertNotNull(saved);
        assertEquals("a", saved.getUsername());


    }
}