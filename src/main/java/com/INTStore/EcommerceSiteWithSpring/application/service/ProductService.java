package com.INTStore.EcommerceSiteWithSpring.application.service;

import com.INTStore.EcommerceSiteWithSpring.application.model.Product;
import com.INTStore.EcommerceSiteWithSpring.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Method to save a product, accessible by Seller and Admin
    @PreAuthorize("hasAuthority('SELLER') or hasAuthority('ADMIN')")
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Method to get a product, accessible by all authenticated users
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Method to delete a product, accessible by Seller and Admin
    @PreAuthorize("hasAuthority('SELLER') or hasAuthority('ADMIN')")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

