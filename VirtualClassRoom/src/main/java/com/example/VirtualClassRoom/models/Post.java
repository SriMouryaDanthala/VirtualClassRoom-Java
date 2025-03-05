package com.example.VirtualClassRoom.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "posts")
public class Post {

    @Id
    @Column(name = "post_id")
    UUID postID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_classroom", referencedColumnName = "Classroom_id")
    ClassRoom postClassRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_user_id", referencedColumnName = "User_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    User postUser;

    String postContent;
    Integer postLikeCount;
    LocalDateTime postDate;

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public UUID getPostID() {
        return postID;
    }

    public void setPostID(UUID postID) {
        this.postID = postID;
    }

    public ClassRoom getPostClassRoom() {
        return postClassRoom;
    }

    public void setPostClassRoom(ClassRoom postClassRoom) {
        this.postClassRoom = postClassRoom;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
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

}
