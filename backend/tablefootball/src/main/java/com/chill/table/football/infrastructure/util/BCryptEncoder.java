package com.chill.table.football.infrastructure.util;

import com.chill.table.football.application.user.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder encoder;

    public BCryptEncoder(final BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(final String rawPassword) {
        return encoder.encode(rawPassword);
    }
}
