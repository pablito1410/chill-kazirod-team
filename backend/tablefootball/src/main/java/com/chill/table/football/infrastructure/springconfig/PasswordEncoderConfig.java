package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.user.PasswordEncoder;
import com.chill.table.football.infrastructure.util.BCryptEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    
    @Bean BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    PasswordEncoder passwordEncoder(final BCryptPasswordEncoder encoder) {
        return new BCryptEncoder(encoder);
    }
}
