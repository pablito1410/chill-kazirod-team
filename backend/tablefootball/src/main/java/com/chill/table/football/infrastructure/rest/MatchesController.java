package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.matches.dto.in.CreateMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.EndMatchRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.EndMatchResponseDTO;
import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.query.matches.MatchProjection;
import com.chill.table.football.architecture.cqrs.CommandGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/matches")
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

    @GetMapping(path = "/{matchId}")
    MatchProjection getOne(@PathParam("matchId") Long matchId) {
        return matchesFinder.findOrThrow(matchId);
    }

    @GetMapping
    List<MatchProjection> getAll() {
        return matchesFinder.findAll();
    }
}
