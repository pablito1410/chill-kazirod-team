package com.chill.table.football.application.matches.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class PlayerAlreadyExistException extends RuntimeException {
    private String userName;

    public PlayerAlreadyExistException(final String userName) {
        this.userName = userName;
    }
}
