package com.petproject.oneance.dto.response;

import lombok.Getter;

@Getter
public class AuthorizationResponse {

    public final String BEARER = "Bearer ";

    private final String headerName = "Authorization";

    private final String headerValue;

    public AuthorizationResponse(String headerValue) {
        this.headerValue = BEARER + headerValue;
    }

}
