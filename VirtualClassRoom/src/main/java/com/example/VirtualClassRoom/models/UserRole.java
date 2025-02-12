package com.example.VirtualClassRoom.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "UserRoles")
public class UserRole {

    @Id
    @Column(name = "Role_id")
    public UUID roleId;
    @Column(name = "Role_name")
    public String roleName;
    @Column(name = "Role_created_at")
    public LocalDateTime createdAt;

    public UserRole(){

    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
