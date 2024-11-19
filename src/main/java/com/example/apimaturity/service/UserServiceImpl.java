package com.example.apimaturity.service;

import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;
import com.example.apimaturity.repository.UserRepo;

// import org.hibernate.mapping.Set; // Remove this line
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepository;

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void addCreatedClient(User user, Client client) {
        if (!user.getCreatedClients().contains(client)) {
            user.getCreatedClients().add(client);
            client.setCreator(user);
            userRepository.save(user);
        }
    }

    @Override
    public List<Client> getCreatedClients(User user) {
        Set<Client> sClients = user.getCreatedClients();
        return new ArrayList<>(sClients);
    }
}