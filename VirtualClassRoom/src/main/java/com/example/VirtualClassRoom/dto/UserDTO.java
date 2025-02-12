package com.example.VirtualClassRoom.dto;

import java.util.UUID;

public class UserDTO {
    public UUID userId;
    public String UserName;
    public String UserFirstName;
    public String UserLastName;
    public String UserRole;

    public UserDTO() {}

    public UserDTO(UUID userId, String userName, String userFirstName, String userLastName, String userRole) {
        this.userId = userId;
        this.UserName = userName;
        this.UserFirstName = userFirstName;
        this.UserLastName = userLastName;
        this.UserRole = userRole;
    }
}
