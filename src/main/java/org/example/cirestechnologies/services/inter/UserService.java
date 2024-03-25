package org.example.cirestechnologies.services.inter;

import org.example.cirestechnologies.entities.DBUser;

import java.util.UUID;

public interface UserService {
    DBUser getUserById(UUID id);

    DBUser getUserByUsername(String username);
}
