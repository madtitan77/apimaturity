package com.example.apimaturity.dto;

import com.example.apimaturity.security.Role;
import com.example.apimaturity.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDTO {
    private String email;
    private String password;
    //default role is user
    private String role = Role.RoleType.USER.toString();

   
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User toEntity(PasswordEncoder passwordEncoder){
        User newUser = new User();
        newUser.setEmail(this.getEmail());
        newUser.setPassword(passwordEncoder.encode(this.getPassword()));
        newUser.setRole(new Role(this.role));
        return newUser;
    }

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        //dto.setRole(user.getRole().getRoleType().toString());
        // Do not set the password for security reasons
        return dto;
    }
}
