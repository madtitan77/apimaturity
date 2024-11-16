package com.example.apimaturity.service;
import com.example.apimaturity.model.User;
import com.example.apimaturity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;


    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    

    
}