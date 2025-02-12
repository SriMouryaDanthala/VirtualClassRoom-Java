package com.example.VirtualClassRoom.repository;

import com.example.VirtualClassRoom.models.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends CrudRepository<UserRole, UUID> {
    List<UserRole> getUserRoleByRoleName(String roleName);
}
