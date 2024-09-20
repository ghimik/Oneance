package com.petproject.oneance.service;

import com.petproject.oneance.security.jwt.JwtTokenGenerator;
import com.petproject.oneance.security.jwt.JwtTokenVerifier;
import com.petproject.oneance.util.KeyReader;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {

    public final String AUTORIZATION = "Authorization";

    private final String PRIVATE_KEY_PATH;

    private final String PUBLIC_KEY_PATH;

    {
    PRIVATE_KEY_PATH = Paths.get(Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource("keys/private_key.pem"))
                .toURI())
                .toString();
    PUBLIC_KEY_PATH = Paths.get(Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource("keys/public_key.pem"))
                .toURI())
                .toString();
}


    private final JwtTokenGenerator generator;

    private final JwtTokenVerifier verifier;

    public JwtService() throws Exception {
        this.generator = new JwtTokenGenerator(KeyReader.getPrivateKey(PRIVATE_KEY_PATH));
        this.verifier = new JwtTokenVerifier(KeyReader.getPublicKey(PUBLIC_KEY_PATH));
    }


    public Map<String, String> generateHeader(UserDetails user) {
        String token = generator.generate(user);
        return Map.of(AUTORIZATION, "Bearer" + token);
    }

}
