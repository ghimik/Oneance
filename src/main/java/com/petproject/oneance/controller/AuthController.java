package com.petproject.oneance.controller;

import com.petproject.oneance.dto.request.AuthorizationRequestDto;
import com.petproject.oneance.dto.response.AuthorizationResponseDto;
import com.petproject.oneance.service.AuthService;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthorizationResponseDto> login
            (@RequestBody AuthorizationRequestDto requestDto) {

        try {
            return ResponseEntity.ok(authService.authorize(requestDto));
        } catch (AuthException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .header("WWW-Authenticate", "enjoi;)")
                    .build();
        }
    }

}
