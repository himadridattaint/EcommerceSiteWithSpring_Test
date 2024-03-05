package com.INTStore.EcommerceSiteWithSpring.application.repository;

import com.INTStore.EcommerceSiteWithSpring.application.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{
    List<Customer> findByMail(String mail);

}
