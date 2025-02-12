package com.example.VirtualClassRoom.repository;

import com.example.VirtualClassRoom.models.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

public interface CredentialsRepository extends CrudRepository<Credentials, UUID> {
    Credentials getCredentialsByUsername(String username);
}
