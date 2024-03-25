package org.example.cirestechnologies.services.impl;

import lombok.AllArgsConstructor;
import org.example.cirestechnologies.entities.DBUser;
import org.example.cirestechnologies.repositories.UserRepository;
import org.example.cirestechnologies.services.inter.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserService {

    private  final UserRepository userRepository;
    @Override
    public DBUser getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public DBUser getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
