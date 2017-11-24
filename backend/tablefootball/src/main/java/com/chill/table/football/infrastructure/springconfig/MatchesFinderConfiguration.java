package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matchesfinder.MatchesFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MatchesFinderConfiguration {

    @Bean
    MatchesFinder matchesFinder() {
        return new MatchesFinder();
    }
}
