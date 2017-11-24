package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.matches.MatchesService;
import com.chill.table.football.application.matches.dto.in.CreateMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.EndMatchRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.EndMatchResponseDTO;
import com.chill.table.football.application.matchesfinder.MatchesFinder;
import com.chill.table.football.architecture.cqrs.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController("api/matches")
class MatchesController {

    private CommandGateway commandGateway;
    private MatchesFinder matchesFinder;

    public MatchesController(CommandGateway commandGateway, MatchesFinder matchesFinder) {
        this.commandGateway = Objects.requireNonNull(commandGateway);
        this.matchesFinder = Objects.requireNonNull(matchesFinder);
    }

    @PostMapping
    CreateMatchResponseDTO createMatch(@RequestBody @Valid CreateMatchRequestDTO requestDTO) {
        return commandGateway.dispatch(requestDTO);
    }

    @PutMapping("/end-match")
    EndMatchResponseDTO endMatch(@RequestBody @Valid EndMatchRequestDTO requestDTO) {
        return commandGateway.dispatch(requestDTO);
    }
}
