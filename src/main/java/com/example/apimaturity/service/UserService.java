package com.example.apimaturity.service;

import com.example.apimaturity.model.User;

import java.util.List;

import com.example.apimaturity.model.Client;


public interface UserService {
    User createUser(User newUser);
    User findByEmail(String email);
    void addCreatedClient(User user, Client client);
    List <Client> getCreatedClients(User user);
}