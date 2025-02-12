package com.example.VirtualClassRoom.dto;

import java.util.UUID;

public class ClassRoomRegistrationDTO {
    public String getClassRoomName() {
        return ClassRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        ClassRoomName = classRoomName;
    }

    public UUID getClassRoomInchargeID() {
        return ClassRoomInchargeID;
    }

    public void setClassRoomInchargeID(UUID classRoomInchargeID) {
        ClassRoomInchargeID = classRoomInchargeID;
    }

    public String getClassRoomDescription() {
        return ClassRoomDescription;
    }

    public void setClassRoomDescription(String classRoomDescription) {
        ClassRoomDescription = classRoomDescription;
    }

    public String ClassRoomName;
    public UUID ClassRoomInchargeID;
    public String ClassRoomDescription;
}
