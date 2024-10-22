package dev.melis.EngelsizGonuller.service.authentication;

import lombok.Getter;


public class AuthenticationServiceRequest {
     private String email;
     private String password;

    public AuthenticationServiceRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public AuthenticationServiceRequest setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
