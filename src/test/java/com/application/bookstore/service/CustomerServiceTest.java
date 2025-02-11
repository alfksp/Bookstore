package com.application.bookstore.service;

import com.application.bookstore.model.Customer;
import com.application.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        customer.setPassword("a");
        customer.setConfirmPassword("a");
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
        assertEquals("a", saved.getConfirmPassword());
    }

    @Test
    void ckeckIfFoundByUsername() {
        when(customerRepository.findByUsername(customer.getUsername())).thenReturn(customer);

        Customer saved = customerService.findByUsername("a");

        assertNotNull(saved);
        assertEquals("a", saved.getUsername());


    }
}