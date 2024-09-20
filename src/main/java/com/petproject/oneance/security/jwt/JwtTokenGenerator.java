package com.petproject.oneance.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
public class JwtTokenGenerator {

    private final PrivateKey privateKey;

    private final Integer EXPIRATION_TIME_MILS = 1000 * 60 * 30;

    public String generate(UserDetails user) {
        Instant now = Clock.systemUTC().instant();

        return Jwts.builder()
                .claim("name", user.getUsername())
                .claim("authorities", user.getAuthorities())
                .issuedAt(Date.from(now))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILS))
                .signWith(privateKey)
                .compact();
    }

}
