package com.disa.expensetrackerapi.domain.dto.auth;

import com.disa.expensetrackerapi.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;

    private String tokenType;

    private Long userId;

    private String username;

    private String email;

    private Role role;
}
