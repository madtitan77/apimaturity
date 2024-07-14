package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.GetMapping; // Add this import statement
import org.springframework.http.ResponseEntity; // Add this import statement
import java.util.List; // Add this import statement
import com.example.apimaturity.service.ClientService;
import com.example.apimaturity.model.Client; // Add this import statement

@RestController
@RequestMapping("/api/apimaturity/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients);
    }
}