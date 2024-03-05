package com.INTStore.EcommerceSiteWithSpring.application.service;

import com.INTStore.EcommerceSiteWithSpring.application.model.Admin;
import com.INTStore.EcommerceSiteWithSpring.application.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin saveNewAdmin(Admin admin) {
        // Encode the password before saving the admin
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        return adminRepository.save(admin);
    }

    // Add other methods as needed
}
