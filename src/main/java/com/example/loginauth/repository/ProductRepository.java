package com.example.loginauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauth.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
