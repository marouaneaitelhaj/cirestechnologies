package org.example.cirestechnologies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
