package com.user.management.UserManagement.Modals;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class JwtToken {

    @Id
    private String token;

    public JwtToken(String token) {

        this.token = token;
    }

    public JwtToken() {
        super();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
