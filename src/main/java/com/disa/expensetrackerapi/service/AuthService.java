package com.disa.expensetrackerapi.service;

import com.disa.expensetrackerapi.domain.dto.auth.AuthInfoResponse;
import com.disa.expensetrackerapi.domain.dto.auth.AuthRequest;
import com.disa.expensetrackerapi.domain.dto.auth.AuthResponse;
import com.disa.expensetrackerapi.domain.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    AuthInfoResponse me();
}
