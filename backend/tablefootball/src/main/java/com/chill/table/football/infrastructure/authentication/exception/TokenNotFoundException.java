package com.chill.table.football.infrastructure.authentication.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenNotFoundException extends AuthenticationException {
    public TokenNotFoundException(final String msg) {
        super(msg);
    }
}
