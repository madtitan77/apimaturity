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
    public void setAssessmentNumber(Integer assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }

    public Integer getAssessmentNumber() {
        return this.assessmentNumber;
    }   

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }       

    public Integer getClientId() {
        return this.clientId;
    }   

    public void setUserId(Integer userId) {
        this.userId = userId;
    }   

    public Integer getUserId() {
        return this.userId;
    }   

    public void setObjective(String objective) {
        this.objective = objective;
    }   

    public String getObjective() {
        return this.objective;
    }       

    public void setName(String name) {
        this.name = name;
    }       

    public String getName() {
        return this.name;
    }   

    public void setUser(User user) {
        this.user = user;
    }       

    public User getUser() {
        return this.user;
    }       

    public void setClient(Client client) {
        this.client = client;
    }   

    public Client getClient() {
        return this.client;
    }   

    
}