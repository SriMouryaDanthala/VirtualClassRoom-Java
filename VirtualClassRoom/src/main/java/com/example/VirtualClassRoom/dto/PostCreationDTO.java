package com.example.VirtualClassRoom.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostCreationDTO {

    UUID postClassRoom;
    UUID postUser;
    String postContent;

    public UUID getPostClassRoom() {
        return postClassRoom;
    }

    public void setPostClassRoom(UUID postClassRoom) {
        this.postClassRoom = postClassRoom;
    }

    public UUID getPostUser() {
        return postUser;
    }

    public void setPostUser(UUID postUser) {
        this.postUser = postUser;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
