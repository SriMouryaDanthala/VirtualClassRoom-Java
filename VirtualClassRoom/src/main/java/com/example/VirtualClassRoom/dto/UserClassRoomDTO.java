package com.example.VirtualClassRoom.dto;

import com.example.VirtualClassRoom.models.UserClassRoom;

import java.util.UUID;

public class UserClassRoomDTO  {
     public UUID userClassRoomID;
     public UUID userID;
     public UUID classRoomID;

    public UUID getClassRoomID() {
        return classRoomID;
    }

    public void setClassRoomID(UUID classRoomID) {
        this.classRoomID = classRoomID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getUserClassRoomID() {
        return userClassRoomID;
    }

    public void setUserClassRoomID(UUID userClassRoomID) {
        this.userClassRoomID = userClassRoomID;
    }
}
