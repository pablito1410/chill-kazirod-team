package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matches.MatchesRepository;
import com.chill.table.football.application.matches.MatchesService;
import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.query.user.UserFinder;
import com.chill.table.football.application.user.UserDao;
import com.chill.table.football.infrastructure.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchesServiceConfiguration {

    @Bean
    public MatchesService matchesService(MatchesRepository matchesRepository, MatchesRepository.TeamRepository teamRepository,
                                         MatchesRepository.PlayerRepository playerRepository, MatchesFinder matchesFinder,
                                         UserDao userDao) {
        return new MatchesService(matchesRepository, teamRepository, playerRepository, matchesFinder, userDao);
    }
}
