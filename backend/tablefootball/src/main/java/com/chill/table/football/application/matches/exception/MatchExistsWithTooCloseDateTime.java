package com.chill.table.football.application.matches.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;

@Value()
@EqualsAndHashCode(callSuper = true)
public class MatchExistsWithTooCloseDateTime extends RuntimeException {
    private LocalDateTime dateTime;
}
