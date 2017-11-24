package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matches.MatchesRepository;
import com.chill.table.football.application.matches.MatchesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchesServiceConfiguration {

    @Bean
    public MatchesService matchesService(MatchesRepository matchesRepository) {
        return new MatchesService(matchesRepository);
    }
}
