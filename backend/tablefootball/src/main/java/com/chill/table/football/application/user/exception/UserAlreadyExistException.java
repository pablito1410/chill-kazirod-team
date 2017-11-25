package com.chill.table.football.application.user.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(final String msg) {
        super(msg);
    }
}
