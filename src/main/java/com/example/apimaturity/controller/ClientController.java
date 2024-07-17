package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping; // Add this import statement
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }
    
    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) { 
        clientService.deleteClient(clientId);
        return ResponseEntity.ok().build();
    }
    
}