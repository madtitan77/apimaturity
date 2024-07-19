package com.example.apimaturity.service;
import com.example.apimaturity.dto.UserCreationDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.apimaturity.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserCreationDTO userCreationDTO) {
        User newUser = new User();
        newUser.setEmail(userCreationDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
        // Validate and set the role
        if (userCreationDTO.getRole() != null && Role.RoleType.isValidRole(userCreationDTO.getRole())) {
            newUser.setRole(userCreationDTO.getRole());
        } else {
            // Handle invalid or null role, e.g., set a default role or throw an exception
            newUser.setRole(Role.RoleType.USER.toString());
        }
        return userRepository.save(newUser);
    }

    

    
}