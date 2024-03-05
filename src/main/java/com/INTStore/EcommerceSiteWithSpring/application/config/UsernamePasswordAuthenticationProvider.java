package com.INTStore.EcommerceSiteWithSpring.application.config;
import com.INTStore.EcommerceSiteWithSpring.application.model.Admin;
import com.INTStore.EcommerceSiteWithSpring.application.model.Customer;
import com.INTStore.EcommerceSiteWithSpring.application.model.Seller;
import com.INTStore.EcommerceSiteWithSpring.application.repository.AdminRepository;
import com.INTStore.EcommerceSiteWithSpring.application.repository.CustomerRepository;
import com.INTStore.EcommerceSiteWithSpring.application.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider
{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password= authentication.getCredentials().toString();

        // Check if the user is a customer
        List<Customer> customers = customerRepository.findByMail(username);
        if (!customers.isEmpty()) {
            if (passwordEncoder.matches(password, customers.get(0).getPassword()))
            {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }

        // Check if the user is a seller
        List<Seller> sellers = sellerRepository.findByMail(username);
        if (!sellers.isEmpty()) {
            if (passwordEncoder.matches(password, sellers.get(0).getPassword()))
            {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("SELLER"));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }

        // Check if the user is an admin
        List<Admin> admins = adminRepository.findByMail(username);
        if (!admins.isEmpty()) {
            if (passwordEncoder.matches(password, admins.get(0).getPassword()))
            {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }

        throw new BadCredentialsException("No user registered with this details!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
