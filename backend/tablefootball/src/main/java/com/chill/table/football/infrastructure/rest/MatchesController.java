package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.matches.MatchesService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchesController {

    private MatchesService matchesService;

    public MatchesController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }
}
