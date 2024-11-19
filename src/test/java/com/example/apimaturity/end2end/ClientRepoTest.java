package com.example.apimaturity.end2end;

import com.example.apimaturity.dto.UserDTO;
import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;
import com.example.apimaturity.repository.ClientRepo;
import com.example.apimaturity.repository.UserRepo;
import com.example.apimaturity.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClientRepoTest {

    private static final Logger logger = LoggerFactory.getLogger(ClientRepoTest.class);

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private String loginUserAndGetToken(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, headers);

        // Log the request details
        logger.info("Request Headers: {}", request.getHeaders());
  
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);
            logger.info("Request JSON: {}", json);
        } catch (JsonProcessingException e) {
            logger.error("Error converting request body to JSON", e);
        }


        ResponseEntity<String> response = restTemplate.postForEntity("/login", request, String.class);

        // Log the response details
        logger.info("Status Code: {}", response.getStatusCode());
        logger.info("Headers: {}", response.getHeaders());
        logger.info("Body: {}", response.getBody());


        assertTrue(response.getStatusCode() == HttpStatus.OK);
        String token = response.getBody();
        return token;
    }

    @BeforeEach
    public void setUp() {
        userRepo.deleteAll();
        clientRepo.deleteAll();
    }

    @Test
    public void testFindByUserId() {
        UserDTO userCreationDTO = new UserDTO();
        userCreationDTO.setEmail("testuser@example.com");
        userCreationDTO.setPassword("password");
        User user = userService.createUser(userCreationDTO.toEntity(passwordEncoder));


        // Retrieve and log all users in the User table
        List<User> users = userRepo.findAll();
        users.forEach(u -> logger.info("User: id={}, name={}, email={}", u.getId(), u.getName(), u.getEmail()));
        
        String token = loginUserAndGetToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        Client client = new Client();
        client.setName("Test Client");
        client.setIndustry("Test Industry");
        client.setCreator(user);

        HttpEntity<Client> request = new HttpEntity<>(client, headers);
        ResponseEntity<Client> response = restTemplate.postForEntity("/clients", request, Client.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        List<Client> clients = clientRepo.findByCreator(user);
        assertNotNull(clients);
        assertFalse(clients.isEmpty());
        clients.forEach(c -> {
            assertNotNull(c.getClientId());
            assertNotNull(c.getName());
            assertNotNull(c.getIndustry());
            assertNotNull(c.getCreator());
        });
    }
}