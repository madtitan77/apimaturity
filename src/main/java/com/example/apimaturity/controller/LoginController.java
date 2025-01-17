package com.example.apimaturity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/apimaturity")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    logger.info("Attempting to authenticate user: {}", loginRequest.getUsername());

    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("User {} authenticated successfully", loginRequest.getUsername());

        // Instead of redirecting, return a success message or token
        // For simplicity, returning a success message. In a real application, you might return a JWT token here.
        return ResponseEntity.ok("User authenticated successfully");
    } catch (Exception e) {
        logger.error("Authentication failed for user: {}", loginRequest.getUsername(), e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}
}

class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}