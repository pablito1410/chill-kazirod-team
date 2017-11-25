package com.chill.table.football.infrastructure.rest.error;

import com.chill.table.football.application.matches.exception.MatchNotFoundException;
import com.chill.table.football.application.matches.exception.PlayerAlreadyExistException;
import com.chill.table.football.application.matches.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerErrorAdvice {

    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handle(PlayerNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(PlayerAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse handle(PlayerAlreadyExistException e) {
        return new ErrorResponse(e.getMessage());
    }
}
