package com.INTStore.EcommerceSiteWithSpring.application.controller;

import com.INTStore.EcommerceSiteWithSpring.application.model.Customer;
import com.INTStore.EcommerceSiteWithSpring.application.model.Product;
import com.INTStore.EcommerceSiteWithSpring.application.service.CustomerService;
import com.INTStore.EcommerceSiteWithSpring.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register-customer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }
}


