package com.example.apimaturity.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.apimaturity.controller.ClientController;
import com.example.apimaturity.model.Client; 
import com.example.apimaturity.repository.ClientRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.apimaturity.security.Role.RoleType;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepo clientRepo;
    @Override
    public List<Client> findClientsForUser(String user, String role) {
        logger.info("User {} with role {} checking clients access", user, role);
        if (RoleType.ADMIN.name().equals(role)) {
            logger.info("User {} is ADMIN", user);
            return findAllClients();
        } else if (RoleType.USER.name().equals(role)) {
            logger.info("User {} is USER", user);
            List<Client> userClients = clientRepo.findByUser(user);
            logger.info("User {} has direct access to {}", user, userClients);
            List<Client> indirectUserClients = clientRepo.findClientsByUserPermission(user); // Store result in a variable
            logger.info("User {} has indirect access to {}", user, indirectUserClients);
            userClients.addAll(indirectUserClients); // Use the stored result
            logger.info("User {} has total access to {}", user, userClients);
            return userClients.stream().distinct().collect(Collectors.toList());
        } else {
            return new ArrayList<>();
    }
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
    public Client saveClient(Client client) {   
        return clientRepo.save(client);
    }
}