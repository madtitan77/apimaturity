package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.example.apimaturity.dto.UserDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO newUserDTO, @RequestHeader Map<String, String> headers) {
        try {
            User user = userService.createUser(newUserDTO.toEntity(passwordEncoder));

            return new ResponseEntity<>(UserDTO.fromEntity(user), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Log the exception details for debugging purposes
            logger.error("Error creating user: " + e.getMessage());
            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
        }
    }

    
}