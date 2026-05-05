package com.disa.expensetrackerapi.domain.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
