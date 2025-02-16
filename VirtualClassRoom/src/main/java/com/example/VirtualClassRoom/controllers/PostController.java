package com.example.VirtualClassRoom.controllers;

import com.example.VirtualClassRoom.dto.PostCreationDTO;
import com.example.VirtualClassRoom.dto.PostRetrievalDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/User/{userID}")
    public ResponseEntity<ApiResponse<List<PostRetrievalDTO>>> getUserPosts(@PathVariable UUID userID) {
        var resp = postService.getUserPosts(userID);
        return new ResponseEntity<>(resp, resp.getHttpStatusCode());
    }

    @GetMapping("/ClassRoom/{classRoomID}")
    public ResponseEntity<ApiResponse<List<PostRetrievalDTO>>> getClassRoomPosts(@PathVariable UUID classRoomID) {
        var resp = postService.getClassRoomPosts(classRoomID);
        return new ResponseEntity<>(resp, resp.getHttpStatusCode());
    }

    @DeleteMapping("/Delete/{postId}")
    public ResponseEntity<ApiResponse<PostRetrievalDTO>> deletePost(@PathVariable UUID postId) {
        var resp = postService.removePost(postId);
        return new ResponseEntity<>(resp, resp.getHttpStatusCode());
    }

    @PostMapping("/Create")
    public ResponseEntity<ApiResponse<PostRetrievalDTO>> createPost(@RequestBody  PostCreationDTO postDTO){
        var resp = postService.createPost(postDTO);
        return new ResponseEntity<>(resp, resp.getHttpStatusCode());
    }
}
