package org.example.cirestechnologies.controller;

import lombok.RequiredArgsConstructor;
import org.example.cirestechnologies.configs.SpringSecurityAuditAwareImpl;
import org.example.cirestechnologies.services.inter.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final SpringSecurityAuditAwareImpl springSecurityAuditAwareImpl;

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile() {
        return ResponseEntity.ok(springSecurityAuditAwareImpl.getCurrentAuditor());
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}
