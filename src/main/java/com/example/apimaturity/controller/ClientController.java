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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;

import java.util.List; // Add this import statement
import com.example.apimaturity.service.ClientService;
import com.example.apimaturity.service.UserService;
import com.example.apimaturity.model.Client; // Add this import statement
import com.example.apimaturity.model.User;
import com.example.apimaturity.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/apimaturity/clients")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Client>> getClients(Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        List<Client> clients = clientService.findClientsUserHasAccessTo(user);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable Integer clientId,
                                            Authentication authentication
    ) {
        User user = getUserFromAuthentication(authentication);
        Client client = clientService.findClientById(clientId);
        if (client == null || !clientService.userHasAccessToClient(user, client)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO,
                                                Authentication authentication ) {

        User user = getUserFromAuthentication(authentication);
        logger.info("Creating Client: {}", clientDTO);
        Client client = clientDTO.toEntity();
        client.setCreator(user);
        Client savedClient = clientService.saveClient(client);
        //userService.addCreatedClient(user, client);
        logger.info("Created Client: {}", savedClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }
    
    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId,
                                             Authentication authentication )
     { 
        User user = getUserFromAuthentication(authentication);
        
        clientService.deleteClient(clientId,user);
        return ResponseEntity.noContent().build();
        
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient( @PathVariable Integer clientId, 
                                                @RequestBody ClientDTO clientDTO,
                                                Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        Client client = clientDTO.toEntity();
        client.setClientId(clientId);
        Client updatedClient = clientService.updateClient(client,user);
        return ResponseEntity.ok(updatedClient);

    }

    private User getUserFromAuthentication(Authentication authentication) {
        //the username is the email of the user... confusing a bit
        //TODO fix the username to be email to avoid confusion
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("User {}", userDetails.getUsername());
        return userService.findByEmail(userDetails.getUsername());
    }
    
}