package com.chill.table.football.application.matches.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AcceptationNotFoundInMatchException extends RuntimeException {
    private Long acceptationId;
}
