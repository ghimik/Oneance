package com.petproject.oneance.security.filter;

import com.petproject.oneance.dto.response.AuthorizationResponse;
import com.petproject.oneance.security.jwt.JwtAuthentication;
import com.petproject.oneance.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final Optional<String> token = getTokenFromRequest(request);
        if (token.isEmpty()) {
            System.out.println("Token is not present");
        } else {
            System.out.println("Token present: " + token.get());
            Claims claims;
            try {
                claims = jwtService.validateToken(token.get());
            } catch (Exception e) {
                filterChain.doFilter(request, response);
                System.out.println("Token is not verified");
                e.printStackTrace();
                return;
            }
            System.out.println("Token verified");
            Authentication authentication = JwtAuthentication.fromClaims(claims);
            System.out.println("Authentication generated");

            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Context set: " + SecurityContextHolder.getContext());

        }
        filterChain.doFilter(request, response);

    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith(AuthorizationResponse.BEARER)) {
            return Optional.of(bearer.substring(AuthorizationResponse.BEARER.length()));
        }
        return Optional.empty();
    }
}
