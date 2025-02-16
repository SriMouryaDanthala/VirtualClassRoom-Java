package com.example.VirtualClassRoom.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostRetrievalDTO {



    UUID postID;
    String postUserName;
    UUID postUserID;
    UUID postClassRoomID;
    String postClassRoomName;
    Integer postLikeCount;
    LocalDateTime postDate;

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    String postContent;

    public UUID getPostClassRoomID() {
        return postClassRoomID;
    }

    public void setPostClassRoomID(UUID postClassRoomID) {
        this.postClassRoomID = postClassRoomID;
    }

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public UUID getPostUserID() {
        return postUserID;
    }

    public void setPostUserID(UUID postUserID) {
        this.postUserID = postUserID;
    }

    public String getPostClassRoomName() {
        return postClassRoomName;
    }

    public void setPostClassRoomName(String postClassRoomName) {
        this.postClassRoomName = postClassRoomName;
    }

    public Integer getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostLikeCount(Integer postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public UUID getPostID() {
        return postID;
    }

    public void setPostID(UUID postID) {
        this.postID = postID;
    }


}
