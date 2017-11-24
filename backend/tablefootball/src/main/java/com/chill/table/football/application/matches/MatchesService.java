package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.CreateMatchDTO;
import com.chill.table.football.application.matches.dto.SetWinnerDTO;

public class MatchesService {

    private MatchesRepository matchesRepository;

    public MatchesService(MatchesRepository matchesRepository) {
        this.matchesRepository = matchesRepository;
    }

    public void createMatch(CreateMatchDTO createMatchDTO) {

    }

    public void setWinner(SetWinnerDTO setWinnerDTO) {

    }
}
