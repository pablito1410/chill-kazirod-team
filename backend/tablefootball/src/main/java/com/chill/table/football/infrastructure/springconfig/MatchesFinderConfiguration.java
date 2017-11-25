package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.query.matches.MatchesFinderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MatchesFinderConfiguration {

    @Bean
    MatchesFinder matchesFinder(MatchesFinderRepository matchesFinderRepository) {
        return new MatchesFinder(matchesFinderRepository);
    }
}
