package com.example.loginauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauth.model.Seller;
import com.example.loginauth.model.User;
import com.example.loginauth.repository.SellerRepository;
import com.example.loginauth.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        String role = user.getRole().toUpperCase(); // Normalize role

        if (role.equals("SELLER")) {
            // Check if seller username already exists
            if (sellerRepository.findByUsername(user.getUsername()) != null) {
                return "Seller username already exists. Please choose another.";
            }

            // Save seller in sellers table
            Seller seller = new Seller();
            seller.setUsername(user.getUsername());
            seller.setPassword(passwordEncoder.encode(user.getPassword()));
            seller.setStoreName("Default Store"); // You can customize this
            sellerRepository.save(seller);

            return "Seller registered successfully!";

        } else if (role.equals("USER")) {
            // Check if user username already exists
            if (userRepository.findByUsername(user.getUsername()) != null) {
                return "User username already exists. Please choose another.";
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
            userRepository.save(user);

            return "User registered successfully!";
        } else {
            return "Invalid role provided. Must be 'USER' or 'SELLER'.";
        }
    }
}
