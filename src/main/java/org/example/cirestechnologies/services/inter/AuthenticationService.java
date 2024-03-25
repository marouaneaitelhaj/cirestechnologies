package org.example.cirestechnologies.services.inter;


import org.example.cirestechnologies.dto.AuthenticationRequest;
import org.example.cirestechnologies.dto.AuthenticationResponse;
import org.example.cirestechnologies.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);
}
