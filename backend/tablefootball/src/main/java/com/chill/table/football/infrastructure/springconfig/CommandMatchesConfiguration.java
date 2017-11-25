package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matches.MatchesService;
import com.chill.table.football.application.matches.dto.in.CreateMatchWithPlayersRequestDTO;
import com.chill.table.football.application.matches.dto.in.EndMatchRequestDTO;
import com.chill.table.football.architecture.cqrs.CommandHandlerRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CommandMatchesConfiguration {

    @Bean
    InitializingBean initializeMatchesCommands(CommandHandlerRegistry registry, MatchesService matchesService) {
        return () -> {
            registry.register(matchesService::createMatchWithPlayers, CreateMatchWithPlayersRequestDTO.class);
            registry.register(matchesService::endMatch, EndMatchRequestDTO.class);
        };
    }
}
