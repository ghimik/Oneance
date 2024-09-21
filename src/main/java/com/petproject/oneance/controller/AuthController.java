package com.petproject.oneance.controller;

import com.petproject.oneance.dto.request.AuthorizationRequestDto;
import com.petproject.oneance.dto.request.SignUpRequestDto;
import com.petproject.oneance.dto.response.AuthorizationResponse;
import com.petproject.oneance.service.AuthService;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> login
            (@RequestBody AuthorizationRequestDto requestDto) {

        try {
            AuthorizationResponse header = authService.authorize(requestDto);
            return successAuth(header);

        } catch (AuthException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .header("WWW-Authenticate", "enjoi;)")
                    .build();
        }
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<Void> signup(@RequestBody SignUpRequestDto requestDto) {

        try {
            AuthorizationResponse header = authService.signup(requestDto);
            return successAuth(header);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private ResponseEntity<Void> successAuth(AuthorizationResponse header) {
        return ResponseEntity
                    .ok()
                    .header(header.getHeaderName(), header.getHeaderValue())
                    .build();
    }

}
