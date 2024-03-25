package org.example.testcirestechnologies.service.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.testcirestechnologies.config.JwtService;
import org.example.testcirestechnologies.dto.AuthenticationRequest;
import org.example.testcirestechnologies.dto.AuthenticationResponse;
import org.example.testcirestechnologies.dto.RegisterRequest;
import org.example.testcirestechnologies.entity.DBUser;
import org.example.testcirestechnologies.enums.Role;
import org.example.testcirestechnologies.repo.UserRepository;
import org.example.testcirestechnologies.service.inter.AuthenticationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Data
@Service
public class AuthenticationService implements AuthenticationServiceInterface {

    private  final UserRepository userRepository;


    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;


    private  final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
       authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        DBUser user = DBUser.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .email(registerRequest.getEmail())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
