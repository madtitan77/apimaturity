package com.example.apimaturity.model;
import jakarta.persistence.*;

@Entity
@Table(name = "user_client")
@IdClass(UserClientId.class)
public class UserClient {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "client_id")
    private Integer clientId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    // getters and setters for all these fields

    // Please note that adding a method similar to Python's `to_dict()`
    // is strongly discouraged in Java -- please use an object mapper (Jackson) instead
}

@Embeddable
class UserClientId {
 
    private Integer userId;

    private Integer clientId;

    // getters and setters

    // equals and hashCode methods;
    // SQLAlchemy does this behind the scenes based on primary key fields
}