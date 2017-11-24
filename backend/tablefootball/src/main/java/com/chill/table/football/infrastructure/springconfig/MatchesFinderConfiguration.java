package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matchesfinder.MatchesFinder;
import com.chill.table.football.application.matchesfinder.MatchesFinderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MatchesFinderConfiguration {

    @Bean
    MatchesFinder matchesFinder(MatchesFinderRepository matchesFinderRepository) {
        return new MatchesFinder(matchesFinderRepository);
    }
}
