package com.example.apimaturity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.apimaturity.dto.ClientDTO;
import com.example.apimaturity.dto.LoginRequestDTO;
import com.example.apimaturity.dto.UserDTO;
import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;
import com.example.apimaturity.security.Role;
import com.example.apimaturity.security.Role.RoleType;
import com.example.apimaturity.service.ClientServiceImpl;
import com.example.apimaturity.service.UserService;
import com.example.apimaturity.repository.ClientRepo;
import com.example.apimaturity.repository.UserRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.apimaturity.security.JwtResponse;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClientServiceDatabaseTest {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private UserRepo userrRepo;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        clientRepo.deleteAll();
    }

    private User newUserAPICall(String email, String password) throws Exception {
        // Check if user already exists
        User existingUser = userrRepo.findByEmail(email);
        if (existingUser != null) {
            return existingUser;
        }

        // Create UserDTO object
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);

        // Convert UserDTO to JSON
        String userJson = objectMapper.writeValueAsString(userDTO);

        // Send POST request to create user
        MvcResult result = mockMvc.perform(post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andReturn();

        // Convert response to User object
        String responseJson = result.getResponse().getContentAsString();
        return objectMapper.readValue(responseJson, User.class);
    }

    private Client newClientAPICall(String name, String notes, String industry, String token) throws Exception {
        // Create ClientDTO object
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(name);
        clientDTO.setIndustry(industry);
        clientDTO.setNotes(notes);

        // Convert ClientDTO to JSON
        String clientJson = objectMapper.writeValueAsString(clientDTO);

        // Send POST request to create client with JWT token
        MvcResult result = mockMvc.perform(post("/api/apimaturity/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(clientJson))
                .andReturn();

        // Convert response to Client object
        String responseJson = result.getResponse().getContentAsString();
        
        return objectMapper.readValue(responseJson, Client.class);
    }
    
    private String loginUserWithAPI(String email, String password) throws Exception {
        // Create LoginRequest object
        LoginRequestDTO loginRequest = new LoginRequestDTO(); 
        loginRequest.setUsername(email);
        loginRequest.setPassword(password);

        // Convert LoginRequest to JSON
        String loginJson = objectMapper.writeValueAsString(loginRequest);

        // Send POST request to authenticate user
        MvcResult result = mockMvc.perform(post("/api/apimaturity/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andReturn();

        // Convert response to JwtResponse object
        String responseJson = result.getResponse().getContentAsString();
        JwtResponse jwtResponse = objectMapper.readValue(responseJson, JwtResponse.class);

        // Return the JWT token
        return jwtResponse.getToken();
    }


    private List<Client> getClientsAPICall(String token) throws Exception {
        MvcResult result = mockMvc.perform(get("/api/apimaturity/clients")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        return objectMapper.readValue(responseJson, new TypeReference<List<Client>>() {});
    }

    private User setUserRoleDirectlyonDB(User user, Role role) {

        User existingUser = userrRepo.findByEmail(user.getEmail());
        //TODO : is the null checking here a good practice?
        if (existingUser != null ) {
            user.setRole(role);
            return userrRepo.save(existingUser);
        } else {
            return null;
        }
    }

    private boolean areClientListsEqual(List<Client> list1, List<Client> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (Client client1 : list1) {
            boolean found = false;
            for (Client client2 : list2) {
                if (client1.getId().equals(client2.getId()) &&
                    client1.getName().equals(client2.getName()) &&
                    client1.getNotes().equals(client2.getNotes()) &&
                    client1.getIndustry().equals(client2.getIndustry())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void whenAdminShouldReturnAllClients() throws Exception {
        User user1 = newUserAPICall("test1@example.com", "password");
        User user2 = newUserAPICall("test2@example.com", "password");
        String token_user1 = loginUserWithAPI("test1@example.com", "password");
        Client client1 = newClientAPICall("Client1", "a Client", "Telco", token_user1);
        String token_user2 = loginUserWithAPI("test2@example.com", "password");
        Client client2 = newClientAPICall("Client2", "another Client", "Telco", token_user1);
        
        //making user1 admin directly in the database. no API is supported yet.
        Role role = new Role();
        role.setRoleType(RoleType.ADMIN);
        setUserRoleDirectlyonDB(user1,role);


        List<Client> allClients = List.of(client1, client2);
        List<Client> result = getClientsAPICall(token_user1);

        assertEquals(allClients.size(), result.size());

        assertTrue(areClientListsEqual(allClients, result));
    }

    
}