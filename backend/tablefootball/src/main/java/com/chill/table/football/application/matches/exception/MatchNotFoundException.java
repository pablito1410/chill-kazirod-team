package com.chill.table.football.application.matches.exception;

import lombok.Getter;

public class MatchNotFoundException extends RuntimeException {

    public MatchNotFoundException(String message) {
        super(message);
    }
}
