package org.example.testcirestechnologies.controller;

import lombok.RequiredArgsConstructor;
import org.example.testcirestechnologies.dto.AuthenticationRequest;
import org.example.testcirestechnologies.dto.RegisterRequest;
import org.example.testcirestechnologies.repo.UserRepository;
import org.example.testcirestechnologies.service.impl.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    private  final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

}
