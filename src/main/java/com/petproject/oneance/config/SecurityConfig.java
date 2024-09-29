package com.petproject.oneance.config;

import com.petproject.oneance.security.filter.JwtAuthFilter;
import com.petproject.oneance.service.AuthService;
import com.petproject.oneance.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.logging.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // cors, csrf api
    // sessions into repo
    // remember me
    // refresh token
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   @Autowired JwtAuthFilter jwtAuthFilter)
            throws Exception {
        return
        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/api/auth/*").permitAll()
                        .anyRequest().authenticated()

                )
                .build();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(@Autowired JwtService authService) {
        return new JwtAuthFilter(authService);
    }
}
