package com.fag.domain.dto;

public class LoginDTO {
    
    private String document;
    private String password;

    public LoginDTO () {}

    public LoginDTO(String document, String password) {
        this.document = document;
        this.password = password;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
}
