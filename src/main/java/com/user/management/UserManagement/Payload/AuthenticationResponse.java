package com.user.management.UserManagement.Payload;

public class AuthenticationResponse {

    private String response;


    public AuthenticationResponse(String response) {
        this.response = response;

    }

    public AuthenticationResponse(){
        super();
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
