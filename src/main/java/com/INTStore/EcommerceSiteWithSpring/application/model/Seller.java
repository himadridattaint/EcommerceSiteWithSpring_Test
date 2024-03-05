package com.INTStore.EcommerceSiteWithSpring.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="seller")
public class Seller
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    private String sellerName;
    private String mail;
    private String password;
    @ManyToMany
    @JoinTable(name="seller_address",
            joinColumns = @JoinColumn(name="seller_id"),
            inverseJoinColumns = @JoinColumn(name="address_id"))
    private Set<Address> addresses = new HashSet<>();
}
