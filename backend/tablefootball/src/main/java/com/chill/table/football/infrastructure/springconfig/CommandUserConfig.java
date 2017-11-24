package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.user.UserService;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.chill.table.football.architecture.cqrs.CommandHandlerRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CommandUserConfig {

    @Bean
    InitializingBean initializeUserCommands(final CommandHandlerRegistry registry,
                                                   final UserService userService) {
        return () -> {
            registry.register(userService::handle, CreateUserCommand.class);
        };
    }
}
