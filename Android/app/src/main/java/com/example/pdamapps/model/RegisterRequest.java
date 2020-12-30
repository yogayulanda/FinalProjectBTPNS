package com.example.pdamapps.model;

import java.time.LocalDate;

public class RegisterRequest {

    private String fullName;
    private String email;
    private String username;
    private String password;
    private String noTelp;
    private String gender;
    private String address;

    public RegisterRequest(String fullName, String email, String username, String password, String noTelp, String gender, String address) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.noTelp = noTelp;
        this.gender = gender;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
