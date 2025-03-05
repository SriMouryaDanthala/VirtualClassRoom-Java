package com.example.VirtualClassRoom.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "classRooms")
public class ClassRoom {
    @Id
    @Column(name = "Classroom_id")
    public UUID classRoomId;

    @Column(name = "Classroom_name")
    public String classRoomName;

    @Column(name = "Classroom_description")
    public String classRoomDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_incharge_id", referencedColumnName = "User_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    public User classRoomIncharge;

    @Column(name = "Classroom_created_time")
    public LocalDateTime classRoomCreatedTime;


    public ClassRoom(){

    }

    public ClassRoom(UUID classRoomId, String classRoomName, String classRoomDescription, User classRoomIncharge, LocalDateTime classRoomCreatedTime) {
        this.classRoomId = classRoomId;
        this.classRoomName = classRoomName;
        this.classRoomDescription = classRoomDescription;
        this.classRoomIncharge = classRoomIncharge;
        this.classRoomCreatedTime = classRoomCreatedTime;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public UUID getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(UUID classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getClassRoomDescription() {
        return classRoomDescription;
    }

    public void setClassRoomDescription(String classRoomDescription) {
        this.classRoomDescription = classRoomDescription;
    }

    public User getClassRoomIncharge() {
        return classRoomIncharge;
    }

    public void setClassRoomInchargeID(User classRoomIncharge) {
        this.classRoomIncharge = classRoomIncharge;
    }

    public LocalDateTime getClassRoomCreatedTime() {
        return classRoomCreatedTime;
    }

    public void setClassRoomCreatedTime(LocalDateTime classRoomCreatedTime) {
        this.classRoomCreatedTime = classRoomCreatedTime;
    }
}
