package com.INTStore.EcommerceSiteWithSpring.application.repository;

import com.INTStore.EcommerceSiteWithSpring.application.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long>
{
    List<Seller> findByMail(String mail);
}
