package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.user.UserService;
import com.chill.table.football.application.user.ports.outgoing.UserDao;
import com.chill.table.football.infrastructure.repository.user.SpringDataUserRepository;
import com.chill.table.football.infrastructure.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserDao userDao(final SpringDataUserRepository userRepository) {
        return new UserRepository(userRepository);
    }

    @Bean
    public UserService userService(final UserDao userDao) {
        return new UserService(userDao);
    }
}
