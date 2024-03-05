package com.INTStore.EcommerceSiteWithSpring.application.controller;

import com.INTStore.EcommerceSiteWithSpring.application.model.Product;
import com.INTStore.EcommerceSiteWithSpring.application.model.Seller;
import com.INTStore.EcommerceSiteWithSpring.application.service.ProductService;
import com.INTStore.EcommerceSiteWithSpring.application.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @PostMapping("/register-product")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/delete-product/{id}")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register-seller")
    public ResponseEntity<Seller> registerSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerService.saveNewSeller(seller);
        return ResponseEntity.ok(savedSeller);
    }
}
