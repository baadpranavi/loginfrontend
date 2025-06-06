package com.example.loginauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}