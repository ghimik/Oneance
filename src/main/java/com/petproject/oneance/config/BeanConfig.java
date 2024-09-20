package com.petproject.oneance.config;

import com.petproject.oneance.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public JwtService jwtService() {
        try {
            return new JwtService();
        }
        catch (Exception e) {
            System.out.println("Private or public key (.pem) not found. ");
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

}
