package com.example.apimaturity.dto;
import com.example.apimaturity.model.Client;

public class ClientDTO {
    private Integer clientId;
    private String name;
    private String industry;
    private String Notes;
    

   
    public ClientDTO() {}

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public String setNotes(String notes) {
        return Notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    
    public static ClientDTO fromEntity(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setClientId(client.getClientId());
        dto.setName(client.getName());
        dto.setIndustry(client.getIndustry());
        dto.setNotes(client.getNotes());
       
        return dto;
    }

    public Client toEntity() {
        Client client = new Client();
        client.setClientId(this.clientId);
        client.setName(this.name);
        client.setIndustry(this.industry);
        client.setNotes(this.Notes);
        return client;
    }

    public Object getNotes() {
        return this.Notes;
    }
}