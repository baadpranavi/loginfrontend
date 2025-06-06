package com.example.loginauth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.loginauth.model.Seller;
import com.example.loginauth.model.User;
import com.example.loginauth.repository.SellerRepository;
import com.example.loginauth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First check in users table
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            );
        }

        // Then check in sellers table
        Seller seller = sellerRepository.findByUsername(username);
        if (seller != null) {
            return new org.springframework.security.core.userdetails.User(
                    seller.getUsername(),
                    seller.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_SELLER"))
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
