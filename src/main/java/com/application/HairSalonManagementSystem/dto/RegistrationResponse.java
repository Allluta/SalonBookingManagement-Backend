package com.application.HairSalonManagementSystem.dto;

public class RegistrationResponse {
    private String jwtToken;


    public RegistrationResponse(String jwt){
        this.jwtToken=jwt;

    }
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}


