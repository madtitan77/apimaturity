package com.example.apimaturity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_permission")
@IdClass(UserPermissionId.class)
public class UserPermission {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", insertable = false, updatable = false)
    private Client client;

    // getters and setters for userId, clientId, user, and client
}

@Embeddable
class UserPermissionId {
    private Integer userId;
    private Integer clientId;

    // getters and setters

    // equals and hashCode methods based on userId and clientId
}