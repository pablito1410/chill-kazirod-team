package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.query.matches.MatchesFinderRepository;
import com.chill.table.football.application.query.player.PlayerFinder;
import com.chill.table.football.application.query.player.PlayerFinderRepository;
import com.chill.table.football.application.query.team.TeamFinder;
import com.chill.table.football.application.query.team.TeamFinderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FinderConfiguration {

    @Bean
    MatchesFinder matchesFinder(MatchesFinderRepository matchesFinderRepository) {
        return new MatchesFinder(matchesFinderRepository);
    }

    @Bean
    TeamFinder teamFinder(TeamFinderRepository teamFinderRepository) {
        return new TeamFinder(teamFinderRepository);
    }

    @Bean
    PlayerFinder playerFinder(PlayerFinderRepository playerFinderRepository) {
        return new PlayerFinder(playerFinderRepository);
    }
}
