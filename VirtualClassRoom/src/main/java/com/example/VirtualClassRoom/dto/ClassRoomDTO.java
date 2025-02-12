package com.example.VirtualClassRoom.dto;

import com.example.VirtualClassRoom.models.User;

import java.util.UUID;

public class ClassRoomDTO {
    UUID classRoomId;
    UUID classRoomInchargeID;
    String classRoomName;
    String classRoomDescription;
    UserDTO classRoomInchargeDetails;

    public ClassRoomDTO() {}
    public ClassRoomDTO(UUID classRoomId, UUID classRoomInchargeID, String classRoomName, String classRoomDescription, UserDTO  classRoomInchargeDetails) {
        this.classRoomId = classRoomId;
        this.classRoomInchargeID = classRoomInchargeID;
        this.classRoomName = classRoomName;
        this.classRoomDescription = classRoomDescription;
        this.classRoomInchargeDetails = classRoomInchargeDetails;
    }

    public UUID getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(UUID classRoomId) {
        this.classRoomId = classRoomId;
    }

    public UUID getClassRoomInchargeID() {
        return classRoomInchargeID;
    }

    public void setClassRoomInchargeID(UUID classRoomInchargeID) {
        this.classRoomInchargeID = classRoomInchargeID;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getClassRoomDescription() {
        return classRoomDescription;
    }

    public void setClassRoomDescription(String classRoomDescription) {
        this.classRoomDescription = classRoomDescription;
    }

    public UserDTO getClassRoomInchargeDetails() {
        return classRoomInchargeDetails;
    }

    public void setClassRoomInchargeDetails(UserDTO classRoomInchargeDetails) {
        this.classRoomInchargeDetails = classRoomInchargeDetails;
    }
}
