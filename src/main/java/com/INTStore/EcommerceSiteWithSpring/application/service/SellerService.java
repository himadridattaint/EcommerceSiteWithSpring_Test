package com.INTStore.EcommerceSiteWithSpring.application.service;

import com.INTStore.EcommerceSiteWithSpring.application.model.Seller;
import com.INTStore.EcommerceSiteWithSpring.application.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller saveNewSeller(Seller seller) {
        // Encode the password before saving the seller
        String encodedPassword = passwordEncoder.encode(seller.getPassword());
        seller.setPassword(encodedPassword);
        return sellerRepository.save(seller);
    }

    // Add other methods as needed
}

