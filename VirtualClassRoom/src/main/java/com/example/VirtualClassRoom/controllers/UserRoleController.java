package com.example.VirtualClassRoom.controllers;

import com.example.VirtualClassRoom.dto.RoleDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserRoles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/Roles")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllRoles() {
        var resp = userRoleService.getAllUserRoles();
        return new ResponseEntity<>(resp, resp.getHttpStatusCode());
    }
}
