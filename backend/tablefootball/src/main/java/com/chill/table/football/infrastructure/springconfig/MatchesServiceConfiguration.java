package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matches.MatchesRepository;
import com.chill.table.football.application.matches.MatchesService;
import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.user.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchesServiceConfiguration {

    @Bean
    public MatchesService matchesService(MatchesRepository matchesRepository, MatchesRepository.TeamRepository teamRepository,
                                         MatchesRepository.AcceptationRepository acceptationRepository, MatchesFinder matchesFinder,
                                         PlayerRepository playerRepository) {
        return new MatchesService(matchesRepository, teamRepository, acceptationRepository, matchesFinder, playerRepository);
    }
}
