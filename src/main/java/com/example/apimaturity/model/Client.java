package com.example.apimaturity.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "industry")
    private String industry;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonBackReference
    private User creator;

    @ManyToMany(mappedBy = "accessibleClients")
    private Set<User> usersWithAccess = new HashSet<>();

    // getters and setters for all these fields
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer id) {
        this.clientId = id;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    public User getCreator() {
        return this.creator;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getNotes() {
        return notes;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<User> getUsersWithAccess() {
        return usersWithAccess;
    }

    public void setUsersWithAccess(Set<User> usersWithAccess) {
        this.usersWithAccess = usersWithAccess;
    }

    public Object getId() {
        return this.clientId;
    }
    
}