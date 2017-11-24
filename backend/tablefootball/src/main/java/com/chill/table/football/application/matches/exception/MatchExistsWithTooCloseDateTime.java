package com.chill.table.football.application.matches.exception;

public class MatchExistsWithTooCloseDateTime extends RuntimeException {

    public MatchExistsWithTooCloseDateTime(String message) {
        super(message);
    }
}
