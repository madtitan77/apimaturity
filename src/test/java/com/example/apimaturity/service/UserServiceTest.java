package com.example.apimaturity.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.apimaturity.dto.UserDTO;
import com.example.apimaturity.model.User;
import com.example.apimaturity.repository.UserRepo;
import com.example.apimaturity.security.Role;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    

    @Test
    public void whenCreatingUserWithoutRole_thenDefaultRoleIsUser() {
        // Setup
        UserDTO dto = new UserDTO();
        dto.setEmail("test@example.com");
        dto.setPassword("password");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        // Action
        User result = userService.createUser(dto.toEntity(passwordEncoder));

        // Assert
        assertEquals(Role.RoleType.USER, result.getRole());
    }
}