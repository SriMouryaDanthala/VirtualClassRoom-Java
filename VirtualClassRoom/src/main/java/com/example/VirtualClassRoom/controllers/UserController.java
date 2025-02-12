package com.example.VirtualClassRoom.controllers;

import com.example.VirtualClassRoom.dto.UserDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{userID}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserDetailsForUserID(@PathVariable UUID userID){
        var resp = userService.getUserForUserID(userID);
        return new ResponseEntity<>(resp,resp.httpStatusCode);
    }

    @GetMapping("/uname/{userName}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserDetailsForUserName(@PathVariable String userName){
        var resp = userService.getUserForUserName(userName);
        return new ResponseEntity<>(resp,resp.httpStatusCode);
    }
}
