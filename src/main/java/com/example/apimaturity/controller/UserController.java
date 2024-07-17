package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apimaturity.dto.UserCreationDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreationDTO userCreationDTO) {
        try {
            User user = userService.createUser(userCreationDTO);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Log the exception details for debugging purposes
            logger.error("Error creating user: " + e.getMessage());
            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
        }
    }

    
}