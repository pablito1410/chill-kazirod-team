package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.user.PasswordEncoder;
import com.chill.table.football.application.user.PlayerRepository;
import com.chill.table.football.application.user.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserServiceConfiguration {

    @Bean
    PlayerService userService(final PlayerRepository playerRepository, final PasswordEncoder passwordEncoder) {
        return new PlayerService(playerRepository, passwordEncoder);
    }

}