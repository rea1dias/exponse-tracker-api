package com.disa.expensetrackerapi.service.impl;

import com.disa.expensetrackerapi.domain.dto.auth.AuthInfoResponse;
import com.disa.expensetrackerapi.domain.dto.auth.AuthRequest;
import com.disa.expensetrackerapi.domain.dto.auth.AuthResponse;
import com.disa.expensetrackerapi.domain.dto.auth.RegisterRequest;
import com.disa.expensetrackerapi.domain.entity.User;
import com.disa.expensetrackerapi.enums.Role;
import com.disa.expensetrackerapi.exception.NotFoundException;
import com.disa.expensetrackerapi.mapper.UserMapper;
import com.disa.expensetrackerapi.repo.UserRepository;
import com.disa.expensetrackerapi.security.JwtService;
import com.disa.expensetrackerapi.service.AuthService;
import com.disa.expensetrackerapi.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final SecurityService securityService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return buildAuthResponse(savedUser, token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsernameOrEmail(
                        request.getUsernameOrEmail(),
                        request.getUsernameOrEmail()
                )
                .orElseThrow(() -> new IllegalArgumentException("Invalid username/email or password"));

        String token = jwtService.generateToken(user);
        return buildAuthResponse(user, token);
    }

    private AuthResponse buildAuthResponse(User user, String token) {
        return new AuthResponse(
                token,
                "Bearer",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public AuthInfoResponse me() {
        Long userId = securityService.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toAuthInfoResponse(user);
    }
}
