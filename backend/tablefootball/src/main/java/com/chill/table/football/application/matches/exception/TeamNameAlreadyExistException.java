package com.chill.table.football.application.matches.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class TeamNameAlreadyExistException extends RuntimeException {
    private String name;
}
