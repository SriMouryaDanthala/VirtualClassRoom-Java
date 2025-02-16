package com.example.VirtualClassRoom.repository;

import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.models.Post;
import com.example.VirtualClassRoom.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {
    List<Post> getPostsByPostClassRoom(ClassRoom postClassRoom);

    List<Post> getPostByPostUser(User postUser);

    Post getByPostID(UUID postID);
}
