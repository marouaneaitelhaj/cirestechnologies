package org.example.testcirestechnologies.service.inter;


import org.example.testcirestechnologies.dto.AuthenticationRequest;
import org.example.testcirestechnologies.dto.AuthenticationResponse;
import org.example.testcirestechnologies.dto.RegisterRequest;

public interface AuthenticationServiceInterface {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);
}
