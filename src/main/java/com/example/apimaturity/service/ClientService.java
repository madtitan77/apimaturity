package com.example.apimaturity.service;

import java.util.List; // Add this import statement
import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;


public interface ClientService {
    List<Client> findAllClients();
   // List<Client> findClientsForUser(String user, String role);
    List<Client> findClientsUserHasAccessTo(User user);
    Client saveClient(Client client);
    boolean deleteClient(Integer clientId, User user);
    Client findClientById(Integer clientId);
    Client updateClient(Client client,User user);
    boolean userHasAccessToClient(User user, Client client);
    
}