package com.example.VirtualClassRoom.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "UserClassRoomJoin")
public class UserClassRoom {
    public UUID getUserClassRoomJoinID() {
        return UserClassRoomJoinID;
    }

    public void setUserClassRoomJoinID(UUID userClassRoomJoinID) {
        UserClassRoomJoinID = userClassRoomJoinID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Id
    @Column(name = "userclassRoomjoin_ID")
    private UUID UserClassRoomJoinID;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "User_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "Classroom_id")
    private ClassRoom classRoom;
}
