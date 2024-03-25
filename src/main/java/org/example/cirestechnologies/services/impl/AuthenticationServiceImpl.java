package org.example.cirestechnologies.services.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.cirestechnologies.configs.JwtService;
import org.example.cirestechnologies.dto.AuthenticationRequest;
import org.example.cirestechnologies.dto.AuthenticationResponse;
import org.example.cirestechnologies.dto.RegisterRequest;
import org.example.cirestechnologies.entities.DBUser;
import org.example.cirestechnologies.enums.Role;
import org.example.cirestechnologies.repositories.UserRepository;
import org.example.cirestechnologies.services.inter.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Data
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

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
