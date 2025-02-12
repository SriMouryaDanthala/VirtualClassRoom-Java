package com.example.VirtualClassRoom.controllers;

import com.example.VirtualClassRoom.dto.ClassRoomDTO;
import com.example.VirtualClassRoom.dto.ClassRoomRegistrationDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ClassRooms")
public class ClassRoomController implements IServiceResponse {

    private final ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @PostMapping("/CreateClassRoom")
    public ResponseEntity<ApiResponse<ClassRoomDTO>> CreateClassRoom(@RequestBody ClassRoomRegistrationDTO classRoomRegistrationDTO) {
        var response = classRoomService.createClassRoom(classRoomRegistrationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/ClassRoom/{classRoomID}")
    public ResponseEntity<ApiResponse<ClassRoomDTO>> GetClassRoom(@PathVariable("classRoomID") UUID classRoomID) {
        var response  = classRoomService.getClassRoom(classRoomID);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @PatchMapping("/UpdateClassRoom")
    public ResponseEntity<ApiResponse<ClassRoomDTO>> UpdateClassRoom(@RequestBody ClassRoomDTO classRoomDTO) {
        var response = classRoomService.updateClassRoom(classRoomDTO);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @DeleteMapping("/Delete/{classRoomID}")
    public ResponseEntity<ApiResponse<ClassRoomDTO>> DeleteClassRoom(@PathVariable("classRoomID") UUID classRoomID) {
        var resp = classRoomService.removeClassRoom(classRoomID);
        return new ResponseEntity<>(resp, resp.getHttpStatusCode());
    }
}
