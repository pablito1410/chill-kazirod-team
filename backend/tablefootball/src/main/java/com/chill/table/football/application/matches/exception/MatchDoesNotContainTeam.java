package com.chill.table.football.application.matches.exception;

public class MatchDoesNotContainTeam extends RuntimeException {
    public MatchDoesNotContainTeam(String message) {
        super(message);
    }
}
