package com.petproject.oneance.dto.request;

import lombok.Value;

@Value
public class SignUpRequestDto {

    String username;

    String password;

    String email;

    String role;
}
