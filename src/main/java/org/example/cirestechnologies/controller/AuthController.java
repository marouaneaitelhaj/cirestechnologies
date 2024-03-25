package org.example.cirestechnologies.controller;

import lombok.RequiredArgsConstructor;
import org.example.cirestechnologies.dto.AuthenticationRequest;
import org.example.cirestechnologies.dto.RegisterRequest;
import org.example.cirestechnologies.repositories.UserRepository;
import org.example.cirestechnologies.services.inter.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
