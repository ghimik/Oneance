package com.petproject.oneance.dto.response;

import lombok.Data;
import lombok.Value;

import java.util.Map;

@Value
public class AuthorizationResponseDto {

    Map<String, String> header;

}
