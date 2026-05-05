package com.disa.expensetrackerapi.service;

import com.disa.expensetrackerapi.domain.entity.User;
import com.disa.expensetrackerapi.exception.UnauthorizedException;
import com.disa.expensetrackerapi.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Unauthorized");
        }
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("Unauthorized"));
        return user.getId();
    }
}
