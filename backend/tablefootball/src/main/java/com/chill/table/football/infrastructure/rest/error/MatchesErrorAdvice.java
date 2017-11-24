package com.chill.table.football.infrastructure.rest.error;

import com.chill.table.football.application.matches.exception.MatchDoesNotContainTeam;
import com.chill.table.football.application.matches.exception.MatchNotFoundException;
import com.chill.table.football.application.matches.exception.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
