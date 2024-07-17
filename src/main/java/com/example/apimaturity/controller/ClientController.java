package com.example.apimaturity.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping; // Add this import statement
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer clientId) {
        Client client = clientService.findClientById(clientId);
        return ResponseEntity.ok(client);
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

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer clientId, @RequestBody Client clientDetails) {
        Client client = clientService.findClientById(clientId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        // Assuming Client class has setters for updating fields
        client.setName(clientDetails.getName());
        client.setIndustry(clientDetails.getIndustry());
        client.setNotes(clientDetails.getNotes());
        final Client updatedClient = clientService.saveClient(client);
        return ResponseEntity.ok(updatedClient);
    }
    
}