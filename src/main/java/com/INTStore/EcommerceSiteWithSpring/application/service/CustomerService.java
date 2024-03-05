package com.INTStore.EcommerceSiteWithSpring.application.service;

import com.INTStore.EcommerceSiteWithSpring.application.model.Customer;
import com.INTStore.EcommerceSiteWithSpring.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveNewCustomer(Customer customer) {
        // Encode the password before saving the customer
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        return customerRepository.save(customer);
    }

    // Add other methods as needed
}

