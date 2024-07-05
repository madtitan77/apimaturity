package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apimaturity.dto.UserCreationDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.service.UserService;




@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserCreationDTO userCreationDTO) {
        User user = userService.createUser(userCreationDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}