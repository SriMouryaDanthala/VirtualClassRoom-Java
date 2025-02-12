package com.example.VirtualClassRoom.repository;

import com.example.VirtualClassRoom.models.ClassRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

public interface ClassRoomRepository extends CrudRepository<ClassRoom, UUID> {
    List<ClassRoom> findClassRoomByClassRoomName(String classRoomName);
}

