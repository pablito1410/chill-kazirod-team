package com.chill.table.football.infrastructure.authentication.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthMethodNotSupportedException extends AuthenticationException {
    public AuthMethodNotSupportedException(final String msg) {
        super(msg);
    }
}
