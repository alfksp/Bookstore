package com.application.bookstore.service;

import com.application.bookstore.model.Customer;
import com.application.bookstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }
}
