package com.petproject.oneance.dto.request;

import lombok.Value;

@Value
public class AuthenticationResponse {

    String refreshToken;

    String accessToken;

}
