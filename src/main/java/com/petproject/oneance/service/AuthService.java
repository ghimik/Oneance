package com.petproject.oneance.service;

import com.petproject.oneance.dto.request.AuthorizationRequestDto;
import com.petproject.oneance.dto.response.AuthorizationResponseDto;
import com.petproject.oneance.model.User;
import com.petproject.oneance.repo.UserRepository;
import com.petproject.oneance.security.jwt.JwtTokenGenerator;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthorizationResponseDto authorize(AuthorizationRequestDto requestDto)
            throws AuthException {
        User user = userRepository.findByUsername(requestDto.getUserName());
        if (user == null)
            throw new AuthException("Username not found");
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword()))
            throw new AuthException("Incorrect password");

        Map<String, String> header = jwtService.generateHeader(user);

        return new AuthorizationResponseDto(header);
    }

}
