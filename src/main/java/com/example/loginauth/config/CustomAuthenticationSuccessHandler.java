package com.example.loginauth.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
       
        String redirectURL = request.getContextPath();

        
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();

            switch (role) {
                case "ROLE_ADMIN":
                    redirectURL += "/dashboard";
                    break;
                case "ROLE_SELLER":
                    redirectURL += "/product.html";
                    break;
                case "ROLE_USER":
                    redirectURL += "/index.html";
                    break;
                default:
                    
                    redirectURL += "/default";
                    break;
            }

            
            break;
        }

        
        response.sendRedirect(redirectURL);
    }
}
