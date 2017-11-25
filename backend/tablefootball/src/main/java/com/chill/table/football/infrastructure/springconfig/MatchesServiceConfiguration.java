package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matches.MatchesRepository;
import com.chill.table.football.application.matches.MatchesService;
import com.chill.table.football.application.matches.PlayerRepository;
import com.chill.table.football.application.matches.TeamRepository;
import com.chill.table.football.application.matchesfinder.MatchesFinder;
import com.chill.table.football.application.user.UserFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchesServiceConfiguration {

    @Bean
    public MatchesService matchesService(MatchesRepository matchesRepository, TeamRepository teamRepository,
                                         PlayerRepository playerRepository, MatchesFinder matchesFinder,
                                         UserFinder userFinder) {
        return new MatchesService(matchesRepository, teamRepository, playerRepository, matchesFinder, userFinder);
    }
}
