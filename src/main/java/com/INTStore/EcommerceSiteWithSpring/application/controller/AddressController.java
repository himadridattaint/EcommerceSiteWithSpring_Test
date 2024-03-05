package com.INTStore.EcommerceSiteWithSpring.application.controller;

import com.INTStore.EcommerceSiteWithSpring.application.model.Address;
import com.INTStore.EcommerceSiteWithSpring.application.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController
{
    @Autowired
    private AddressService addressService;

    @GetMapping("/all-address")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Address> getAllAddress()
    {
        return addressService.getAllAddresses();
    }

    @PostMapping("/register-address/customer/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public void saveNewAddressForCustomer(@RequestBody Address address,@PathVariable Long id)
    {
        addressService.saveNewAddressForCustomer(address,id);
    }

    @PostMapping("/register-address/seller/{id}")
    @PreAuthorize("hasAuthority('SELLER')")
    public void saveNewAddressForSeller(@RequestBody Address address,@PathVariable Long id)
    {
        addressService.saveNewAddressForSeller(address,id);
    }
}

