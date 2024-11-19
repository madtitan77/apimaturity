package com.example.apimaturity.repository;

import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;
import com.example.apimaturity.security.Role;
import com.example.apimaturity.security.Role.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class ClientRepoTest {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private UserRepo userRepo;

    private User user1;
    private User user2;
    private Client client1;
    private Client client2;

    @BeforeEach
    public void setUp() {
        Role userRole = new Role(RoleType.USER.toString());
        //Role adminRole = new Role(RoleType.ADMIN.toString());

        // Create users
        user1 = new User();
        user1.setEmail("user1@example.com");
        user1.setPassword("password");
        user1.setRole(userRole);


        user2 = new User();
        user2.setEmail("user2@example.com");
        user2.setPassword("password");
        user2.setRole(userRole);


        user1 = userRepo.save(user1);
        user2 = userRepo.save(user2);

        // Create clients
        client1 = new Client();
        client1.setName("Client One");
        client1.setIndustry("Industry One");
        client1.setNotes("Notes One");
        client1.setCreator(user1);

        client2 = new Client();
        client2.setName("Client Two");
        client2.setIndustry("Industry Two");
        client2.setNotes("Notes Two");
        client2.setCreator(user1);

        client1 = clientRepo.save(client1);
        client2 = clientRepo.save(client2);

        // Grant access to user2 for client1
        /*Set<User> usersWithAccess = new HashSet<>();
        usersWithAccess.add(user2);
        client1.setUsersWithAccess(usersWithAccess);
        client1 = clientRepo.save(client1);*/

        //Grant access to user2 for client1
        Set<Client> clientsToProvideAccessTo = new HashSet<>();
        clientsToProvideAccessTo.add(client1);
        user2.setAccessibleClients(clientsToProvideAccessTo);
        user2 = userRepo.save(user2);

    }

    @Test
    public void testFindByUsersWithAccess() {
        List<Client> clients = clientRepo.findByUsersWithAccess(user2);
        assertEquals(1, clients.size());
        assertTrue(clients.contains(client1));
    }
}