package com.example.loginauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauth.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUsername(String username);
}
