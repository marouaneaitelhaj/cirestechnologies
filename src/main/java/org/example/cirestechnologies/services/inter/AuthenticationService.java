package org.example.cirestechnologies.services.inter;


import org.example.cirestechnologies.dto.AuthenticationRequest;
import org.example.cirestechnologies.dto.AuthenticationResponse;
import org.example.cirestechnologies.dto.RegisterRequest;
import org.example.cirestechnologies.entities.DBUser;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);

    DBUser getUserDbUser(String name);
}
