package com.example.VirtualClassRoom.controllers;

import com.example.VirtualClassRoom.dto.UserDTO;
import com.example.VirtualClassRoom.dto.UserRegistrationDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Registration")

public class RegistrationController {
    private final RegistrationService registrationService;

    RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody UserRegistrationDTO registrationDTO) {
        var response =registrationService.registerUser(registrationDTO);
        return new ResponseEntity<>(response,response.getHttpStatusCode());
    }
}
