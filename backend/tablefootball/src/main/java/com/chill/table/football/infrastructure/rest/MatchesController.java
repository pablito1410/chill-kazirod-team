package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.matches.MatchesService;
import com.chill.table.football.application.matches.dto.in.CreateMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.SetWinnerRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.SetWinnerResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("api/matches")
class MatchesController {

    private MatchesService matchesService;

    MatchesController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @PostMapping
    CreateMatchResponseDTO createMatch(@RequestBody @Valid CreateMatchRequestDTO requestDTO) {
        return matchesService.createMatch(requestDTO);
    }

    @PutMapping("/set-winner")
    SetWinnerResponseDTO setWinner(@RequestBody @Valid SetWinnerRequestDTO requestDTO) {
        return matchesService.setWinner(requestDTO);
    }
}
