package com.example.apimaturity.service;

import com.example.apimaturity.model.User;

public interface UserService {
    User createUser(User newUser);
    User findByEmail(String email);
}