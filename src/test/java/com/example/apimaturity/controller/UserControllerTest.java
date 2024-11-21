package com.example.apimaturity.controller;

import com.example.apimaturity.dto.UserDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.security.Role;
import com.example.apimaturity.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

//@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    //@Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks UserController userController;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");
        userDTO.setPassword("password");
        userDTO.setRole(Role.RoleType.USER.toString());


        User user = new User();
        user.setEmail("test@test.com");
        user.setName("John Doe");

        
        when(userService.createUser(any(User.class))).thenReturn(
                            user
                            );

        mockMvc.perform(post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\""+userDTO.getEmail()+
                            "\",\"password\":\""+userDTO.getPassword()+
                            "\"}"
                        )
                        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is("test@test.com")));
    }
}
