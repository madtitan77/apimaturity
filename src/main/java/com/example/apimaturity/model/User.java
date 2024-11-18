package com.example.apimaturity.model;

import com.example.apimaturity.security.Role;
import com.example.apimaturity.security.Role.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role.RoleType role;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "creator")
    private Set<Client> createdClients = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "user_client_access",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private Set<Client> accessibleClients = new HashSet<>();

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return new Role(this.role.name());
    }

    public void setRole(Role role) {
        this.role = role.getRoleType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Client> getCreatedClients() {
        return createdClients;
    }

    public void setCreatedClients(Set<Client> createdClients) {
        this.createdClients = createdClients;
    }

    public Set<Client> getAccessibleClients() {
        return accessibleClients;
    }

    public void setAccessibleClients(Set<Client> accessibleClients) {
        this.accessibleClients = accessibleClients;
    }

    public boolean isAdmin() {

        return this.role.equals(RoleType.ADMIN);
    
    }
    
}