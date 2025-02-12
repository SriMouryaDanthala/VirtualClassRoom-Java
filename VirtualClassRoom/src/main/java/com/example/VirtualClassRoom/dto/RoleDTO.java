package com.example.VirtualClassRoom.dto;

import java.util.UUID;

public class RoleDTO {
    public UUID roleID;
    public String roleName;

    RoleDTO(){

    }

    public RoleDTO(UUID roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public UUID getRoleID() {
        return roleID;
    }

    public void setRoleID(UUID roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
