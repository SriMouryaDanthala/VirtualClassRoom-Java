package com.example.VirtualClassRoom.controllers;
import com.example.VirtualClassRoom.dto.ClassRoomDTO;
import com.example.VirtualClassRoom.dto.UserClassRoomDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.service.UserClassRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/UserClassRoom")
public class UserClassRoomController {
    private final UserClassRoomService userClassRoomService;

    public UserClassRoomController(UserClassRoomService userClassRoomService) {
        this.userClassRoomService = userClassRoomService;
    }

    @GetMapping("/User/{userID}/ClassRooms")
    public ResponseEntity<ApiResponse<List<ClassRoomDTO>>>getClassRoomsEnrolledByUsers(@PathVariable("userID") UUID userID){
        var response = userClassRoomService.retrieveClassRoomsEnrolledByUser(userID);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @PostMapping("/Enroll")
    public ResponseEntity<ApiResponse<UserClassRoomDTO>> enrollUserInClassRoom(@RequestBody UserClassRoomDTO userClassRoomDTO){
        var response = userClassRoomService.enrollUserInClassRoom(userClassRoomDTO);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @DeleteMapping("/Remove")
    public ResponseEntity<ApiResponse<UserClassRoomDTO>> deleteUserInClassRoom(@RequestBody UserClassRoomDTO userClassRoomDTO){
        var response = userClassRoomService.removeUserFromClassRoom(userClassRoomDTO);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }
}
