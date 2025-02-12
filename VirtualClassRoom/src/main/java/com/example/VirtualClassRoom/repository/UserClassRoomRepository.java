package com.example.VirtualClassRoom.repository;

import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.models.UserClassRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


public interface UserClassRoomRepository extends CrudRepository<UserClassRoom, UUID> {
    List<UserClassRoom> findUserClassRoomByUser(User user);

    UserClassRoom findUserClassRoomByUserAndClassRoom(User user, ClassRoom classRoom);
}
