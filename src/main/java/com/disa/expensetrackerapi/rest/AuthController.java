package com.disa.expensetrackerapi.rest;

import com.disa.expensetrackerapi.domain.dto.auth.AuthInfoResponse;
import com.disa.expensetrackerapi.domain.dto.auth.AuthRequest;
import com.disa.expensetrackerapi.domain.dto.auth.AuthResponse;
import com.disa.expensetrackerapi.domain.dto.auth.RegisterRequest;
import com.disa.expensetrackerapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public ResponseEntity<AuthInfoResponse> me() {
        return ResponseEntity.status(HttpStatus.FOUND).body(authService.me());
    }
}
