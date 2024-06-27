package com.example.apimaturity.model;

import jakarta.persistence.*;
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
    @Column(name = "notes")
    private String notes;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<UserClient> userClients;

    // getters and setters for all these fields

    // Please note that adding a method similar to Python's `to_dict()`
    // is strongly discouraged in Java -- please use an object mapper (Jackson) instead
}