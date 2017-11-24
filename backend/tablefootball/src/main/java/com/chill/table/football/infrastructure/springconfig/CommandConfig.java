package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.architecture.cqrs.CommandHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

    @Bean
    CommandHandlerRegistry registry() {
        return new CommandHandlerRegistry();
    }
}
