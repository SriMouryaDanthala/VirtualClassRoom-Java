package com.example.VirtualClassRoom.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity(name = "credentials")
public class Credentials {

    @Id
    @Column(name = "Credentials_id")
    private UUID credentialsId;
    @Column(name = "Credentials_user_name")
    private String username;
    @Column(name = "Credentials_password")
    private String password;
    @Column(name = "Credentials_created_time")
    private LocalTime createdTime;

    public Credentials() {}

    public Credentials(UUID credentialsId, String username, String password, LocalTime createdTime) {
        this.credentialsId = credentialsId;
        this.username = username;
        this.password = password;
        this.createdTime = createdTime;
    }

    public UUID getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(UUID credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
