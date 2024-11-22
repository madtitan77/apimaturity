package com.example.apimaturity.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;
import com.example.apimaturity.security.Role;
import com.example.apimaturity.repository.ClientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;




public class ClientServiceImplTest {

    @Mock
    private ClientRepo clientRepo;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenAdminShouldReturnAllClients() {

        /*create a User */
        Role adminRole = new Role("ADMIN");
        User user1 = new User();
        user1.setEmail("email@example.com");
        user1.setRole(adminRole);
        User user2 = new User();
        user2.setEmail("email@example1.com");

        /*create two clients */
        Client client1 = new Client();
        Client client2 = new Client();
        client1.setCreator(user1);
        client2.setCreator(user2);
        
        /*create two clients*/
        List<Client> allClients = List.of(client1,client2);
        when(clientRepo.findAll()).thenReturn(allClients);

        List<Client> result = clientService.findClientsUserHasAccessTo(user1);

        assertEquals(allClients.size(), result.size());
        
    }

   
}