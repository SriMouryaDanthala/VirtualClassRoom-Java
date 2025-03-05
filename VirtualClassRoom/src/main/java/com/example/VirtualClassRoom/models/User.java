package com.example.VirtualClassRoom.models;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;
@Entity(name = "Users")
public class User {

    @Id
    @Column(name = "User_id")
    private UUID userId;

    @Column(name = "User_username")
    private String userName;

    @Column(name = "User_first_name")
    private String userFirstName;

    @Column(name = "User_last_name")
    private String userLastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_role_id",referencedColumnName = "Role_id")
    private UserRole userRole;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "User_credentials_id", referencedColumnName = "Credentials_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Credentials credentials;

    @OneToMany(mappedBy = "classRoomIncharge", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ClassRoom> classRooms = new ArrayList<>();

    public User() { }

    public User(UUID userId, String userName, String userFirstName, String userLastName, com.example.VirtualClassRoom.models.UserRole userRole, Credentials credentials) {
        this.userId = userId;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userRole = userRole;
        this.credentials = credentials;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
