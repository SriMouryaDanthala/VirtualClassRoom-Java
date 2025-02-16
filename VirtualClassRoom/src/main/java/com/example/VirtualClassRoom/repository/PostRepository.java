package com.example.VirtualClassRoom.repository;

import com.example.VirtualClassRoom.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {
}
