package com.example.apimaturity.dto;

public class AssessmentGroupDTO {
    private Integer assessmentNumber;
    private Integer clientId;
    private Integer userId;
    private String objective;
    private String name;

    // Getters and Setters
    public Integer getAssessmentNumber() {
        return assessmentNumber;
    }   

    public Integer getClientId() {
        return clientId;
    }   

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }   

    public Integer getUserId() {
        return userId;
    }   

    public void setUserId(Integer userId) {
        this.userId = userId;
    }   

    public String getObjective() {
        return objective;
    }       

    public void setObjective(String objective) {
        this.objective = objective;
    }   

    public String getName() {
        return name;
    }       

    public void setName(String name) {
        this.name = name;
    }       

    
    

} 