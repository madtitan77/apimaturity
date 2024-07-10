package com.example.apimaturity.security;


public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    // You can add more fields here, such as roles, permissions, etc.

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // Getters (and setters if necessary)
    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    // Add setters if you need to modify the fields after object creation
}