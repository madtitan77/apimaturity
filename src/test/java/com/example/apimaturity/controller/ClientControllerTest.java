
package com.example.apimaturity.controller;

import com.example.apimaturity.dto.ClientDTO;
import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;
import com.example.apimaturity.service.ClientService;
import com.example.apimaturity.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void testCreateClient() throws Exception {

        // Create ClientDTO object
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test Client");
        clientDTO.setIndustry("Test Industry");
        clientDTO.setNotes("Test Notes");

        // Mock user details
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("testuser")
                .password("password")
                .authorities("ROLE_USER")
                .build();
        when(authentication.getPrincipal()).thenReturn(userDetails);

        // Mock client service
        Client client = new Client();
        client.setClientId(1);
        client.setName("Test Client");
        client.setIndustry("Test Industry");
        client.setNotes("Test Notes");

        when(clientService.saveClient(any(Client.class))).thenReturn(client);

        // Perform POST request to create client
        mockMvc.perform(post("/api/apimaturity/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + clientDTO.getName() + "\",\"industry\":\"" 
                                + clientDTO.getIndustry() + "\",\"notes\":\"" 
                                + clientDTO.getNotes() + "\"}"
                                )
                        .principal(authentication))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.clientId").value(1))
                        .andExpect(jsonPath("$.name").value(clientDTO.getName()))
                        .andExpect(jsonPath("$.industry").value(clientDTO.getIndustry()))
                        .andExpect(jsonPath("$.notes").value(clientDTO.getNotes()));
    }

    @Test
    void testUpdateClient() throws Exception {

        // Create ClientDTO object
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Updated Client");
        clientDTO.setIndustry("Updated Industry");
        clientDTO.setNotes("Updated Notes");

        User user = new User();
        user.setEmail(" emain@example.com");

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("testuser")
                .password("password")
                .authorities("ROLE_USER")
                .build();
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userService.findByEmail(anyString())).thenReturn(user);
   

        // Mock client service
        Client client = new Client();
        client.setClientId(1);
        client.setName("Updated Client");
        client.setIndustry("Updated Industry");
        client.setNotes("Updated Notes");

        when(clientService.updateClient(any(Client.class))).thenReturn(client);
        when(userService.getCreatedClients(any(User.class))).thenReturn(new ArrayList<>(java.util.Collections.singletonList(client)));

        // Perform PUT request to update client
        mockMvc.perform(put("/api/apimaturity/clients/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"" + clientDTO.getName() + "\",\"industry\":\"" 
                + clientDTO.getIndustry() + "\",\"notes\":\"" 
                + clientDTO.getNotes() + "\"}"
                )
                .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1))
                .andExpect(jsonPath("$.name").value(clientDTO.getName()))
                .andExpect(jsonPath("$.industry").value(clientDTO.getIndustry()))
                .andExpect(jsonPath("$.notes").value(clientDTO.getNotes()));
    }
}