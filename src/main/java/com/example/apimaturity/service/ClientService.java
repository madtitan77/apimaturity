package com.example.apimaturity.service;

import java.util.List; // Add this import statement
import com.example.apimaturity.model.Client;


public interface ClientService {
    List<Client> findAllClients();
    Client saveClient(Client client);
    void deleteClient(Integer clientId);
}