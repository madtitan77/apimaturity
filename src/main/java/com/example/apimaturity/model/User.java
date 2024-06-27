package com.example.apimaturity.model;
import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAssessment> userAssessments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserClient> userClients;

    // getters and setters for the above fields
}