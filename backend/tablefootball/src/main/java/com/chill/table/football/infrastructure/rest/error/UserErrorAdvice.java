package com.chill.table.football.infrastructure.rest.error;

import com.chill.table.football.application.matches.exception.MatchNotFoundException;
import com.chill.table.football.application.user.exception.UserAlreadyExistException;
import com.chill.table.football.application.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserErrorAdvice {

    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handle(UserNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse handle(UserAlreadyExistException e) {
        return new ErrorResponse(e.getMessage());
    }
}
