package com.example.VirtualClassRoom.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostRetrievalDTO {

    String postUserName;
    UUID postUserID;
    UUID postClassRoomID;
    String postClassRoomName;
    Integer postLikeCount;
    LocalDateTime postDate;

}
