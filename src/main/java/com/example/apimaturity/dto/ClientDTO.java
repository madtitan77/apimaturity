package com.example.apimaturity.dto;
import com.example.apimaturity.model.Client;

public class ClientDTO {
    private Integer clientId;
    private String name;
    private String industry;
    

   
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
       
        return dto;
    }
}