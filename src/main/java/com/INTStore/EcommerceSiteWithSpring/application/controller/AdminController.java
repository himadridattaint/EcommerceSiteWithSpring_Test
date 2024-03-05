package com.INTStore.EcommerceSiteWithSpring.application.controller;

import com.INTStore.EcommerceSiteWithSpring.application.model.Admin;
import com.INTStore.EcommerceSiteWithSpring.application.model.Product;
import com.INTStore.EcommerceSiteWithSpring.application.service.AdminService;
import com.INTStore.EcommerceSiteWithSpring.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/register-product")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/delete-product/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/register-admin")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveNewAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

}
