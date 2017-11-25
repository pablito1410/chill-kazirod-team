package com.chill.table.football.infrastructure.rest.error;

import com.chill.table.football.application.matches.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// TODO fix get message!
@RestControllerAdvice
class MatchesErrorAdvice {

    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleMatchNotFoundException(MatchNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleTeamNotFoundException(TeamNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(MatchDoesNotContainTeam.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ErrorResponse handleMatchDoesNotContainTeam(MatchDoesNotContainTeam e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(MatchExistsWithTooCloseDateTime.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ErrorResponse handleMatchExistsWithTooCloseDateTime(MatchExistsWithTooCloseDateTime e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(TeamNameAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ErrorResponse handleTeamNameAlreadyExistException(TeamNameAlreadyExistException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(AcceptationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleAcceptationNotFoundException(AcceptationNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }
}
