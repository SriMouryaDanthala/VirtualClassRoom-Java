package com.example.VirtualClassRoom.dto;

import java.util.UUID;

public class UserRegistrationDTO {
    public String UserFirstName;
    public String UserLastName;
    public String UserName;
    public String UserPassword;
    public UUID UserRole;

    public String getUserFirstName() {
        return UserFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        UserFirstName = userFirstName;
    }

    public String getUserLastName() {
        return UserLastName;
    }

    public void setUserLastName(String userLastName) {
        UserLastName = userLastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public UUID getUserRole() {
        return UserRole;
    }

    public void setUserRole(UUID userRole) {
        UserRole = userRole;
    }
}