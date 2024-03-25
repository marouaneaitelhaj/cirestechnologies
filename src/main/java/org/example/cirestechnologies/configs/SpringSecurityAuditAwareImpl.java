package org.example.wimelody.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.wimelody.entities.DBUser;
import org.example.wimelody.services.impl.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class SpringSecurityAuditAwareImpl {

    private final AuthenticationService authenticationService;

    private final ModelMapper modelMapper;

    public DBUser getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationService.getUserDbUser(authentication.getName());
    }
}