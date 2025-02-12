package com.example.VirtualClassRoom.repository;

import com.example.VirtualClassRoom.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findByUserName(String username);
}
