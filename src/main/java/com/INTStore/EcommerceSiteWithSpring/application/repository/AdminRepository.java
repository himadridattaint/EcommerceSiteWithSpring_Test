package com.INTStore.EcommerceSiteWithSpring.application.repository;

import com.INTStore.EcommerceSiteWithSpring.application.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>
{
    List<Admin> findByMail(String mail);
}
