package com.example.apimaturity.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.apimaturity.controller.ClientController;
import com.example.apimaturity.model.Client; 
import com.example.apimaturity.repository.ClientRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.apimaturity.model.User;
import com.example.apimaturity.repository.UserRepo;
import com.example.apimaturity.security.SecurityUtils;

import com.example.apimaturity.security.Role.RoleType;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private UserRepo userRepo;

    
    @Override
    public List<Client> findClientsUserHasAccessTo(User user){

        if (user.isAdmin()) return clientRepo.findAll();
        return clientRepo.findByCreator(user);
        
    }
    
    @Override
    public List<Client> findAllClients() {
        return clientRepo.findAll();
    }

    @Override
    public Client findClientById(Integer clientId) {
        return clientRepo.findById(clientId).orElse(null);
    }

    @Override
    public void deleteClient(Integer clientId) {
    clientRepo.deleteById(clientId);
    }

    @Override
    @Transactional
    public Client saveClient(Client client) {
        String email = SecurityUtils.getCurrentUserUsername();
        logger.info("User {} is creating a new client {}", email,client.getName());
        User user = userRepo.findByEmail(email);
        client.setCreator(user);
        return clientRepo.save(client);
    }


    @Override
    public Client updateClient(Client client) {
        Client existingClient = clientRepo.findById(client.getClientId()).orElse(null);
        if (existingClient != null) {
            existingClient.setName(client.getName());
            existingClient.setIndustry(client.getIndustry());
            // Update other fields as necessary
            return clientRepo.save(existingClient);
        } else {
            return null;
        }
    }

    public boolean userHasAccessToClient(User user, Client client) {
        return client.getCreator().equals(user) || client.getUsersWithAccess().contains(user);
    }
}