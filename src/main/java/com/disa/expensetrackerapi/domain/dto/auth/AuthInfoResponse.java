package com.disa.expensetrackerapi.domain.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfoResponse {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String role;
}
