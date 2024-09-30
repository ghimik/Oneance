package com.petproject.oneance.service;

import com.petproject.oneance.dto.request.AuthenticationResponse;
import com.petproject.oneance.dto.request.AuthorizationRequestDto;
import com.petproject.oneance.dto.request.SignUpRequestDto;
import com.petproject.oneance.dto.request.TokenRequest;
import com.petproject.oneance.dto.response.AuthorizationResponse;
import com.petproject.oneance.model.User;
import com.petproject.oneance.repo.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    public AuthorizationResponse authorize(AuthorizationRequestDto requestDto)
            throws AuthException {
        User user = userRepository.findByUsername(requestDto.getUsername());
        if (user == null)
            throw new AuthException("Username not found");
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword()))
            throw new AuthException("Incorrect password");

        System.out.println("DB layer: user found!");
        return new AuthorizationResponse(jwtService.generateToken(user));
    }

    public AuthorizationResponse signup(SignUpRequestDto requestDto) {
        User user = User
                .builder()
                .username(requestDto.getUsername())
                .passwordHash(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getEmail())
                .role(User.UserRole.valueOf(requestDto.getRole().toUpperCase()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.saveUserWithRole(user);
        return new AuthorizationResponse(jwtService.generateToken(user));

    }

    public AuthenticationResponse refresh(TokenRequest tokenRequest) {
        Claims claims = jwtService.validateToken(tokenRequest.getRefreshToken());
        String username = claims.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String newAccessToken = jwtService.generateToken(userDetails);
        String newRefreshToken = jwtService.generateRefresh(userDetails);

        return new AuthenticationResponse(
                newRefreshToken,
                newAccessToken
        );
    }


}
