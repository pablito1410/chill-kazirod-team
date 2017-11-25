package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.matches.dto.in.CreateUserRequestDTO;
import com.chill.table.football.application.user.PlayerService;
import com.chill.table.football.architecture.cqrs.CommandHandlerRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CommandUserConfig {

    @Bean
    InitializingBean initializeUserCommands(CommandHandlerRegistry registry,
                                            PlayerService playerService) {
        return () -> {
            registry.register(playerService::handle, CreateUserRequestDTO.class);
        };
    }
}
