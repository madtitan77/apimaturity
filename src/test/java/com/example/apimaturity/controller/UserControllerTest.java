package com.example.apimaturity.controller;

import com.example.apimaturity.dto.UserDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateUserWithPostRequest() throws Exception {
        UserDTO userCreationDTO = new UserDTO();
        userCreationDTO.setEmail("testuser@example.com");
        userCreationDTO.setPassword("password");

        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password");

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        String userCreationDTOJson = objectMapper.writeValueAsString(userCreationDTO);
        logger.debug("UserCreationDTO JSON: " + userCreationDTOJson);

        mockMvc.perform(post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userCreationDTOJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(userCreationDTO.getEmail()));
    }
}