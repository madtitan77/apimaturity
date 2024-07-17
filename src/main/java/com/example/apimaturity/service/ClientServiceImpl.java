package com.example.apimaturity.service;

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 
import com.example.apimaturity.model.Client; 
import com.example.apimaturity.repository.ClientRepo; 
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepo clientRepo;
    @Override
    public List<Client> findAllClients() {
        return clientRepo.findAll();
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