package com.example.apimaturity.model;

import jakarta.persistence.*;


@Entity
@Table(name = "assessment_groups")
public class AssessmentGroup {

    @Id
    @Column(name = "assessment_number")
    private Integer assessmentNumber;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "user_id")
    private Integer userId;

    @Lob
    @Column(name = "objective")
    private String objective;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="client_id", insertable = false, updatable = false)
    private Client client;

    // getters and setters for all these fields
}