package com.petproject.oneance.dto.request;

import lombok.Data;
import lombok.Value;

@Value
public class AuthorizationRequestDto {

    String userName;

    String password;

}
