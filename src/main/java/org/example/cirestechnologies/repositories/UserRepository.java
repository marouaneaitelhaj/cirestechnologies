package org.example.cirestechnologies.repositories;



import org.example.cirestechnologies.entities.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<DBUser, UUID> {
    Optional<DBUser> findByUsername(String username);
}