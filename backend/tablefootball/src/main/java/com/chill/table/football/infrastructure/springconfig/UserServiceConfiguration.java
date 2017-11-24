package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.user.UserService;
import com.chill.table.football.application.user.ports.outgoing.UserDao;
import com.chill.table.football.application.util.EntityMapper;
import com.chill.table.football.infrastructure.repository.user.SpringDataUserRepository;
import com.chill.table.football.infrastructure.repository.user.UserRepository;
import com.chill.table.football.infrastructure.util.EntityMapperDefault;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserDao userDao(final SpringDataUserRepository userRepository) {
        return new UserRepository(userRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public EntityMapper entityMapper(final ModelMapper modelMapper) {
        return new EntityMapperDefault(modelMapper);
    }

    @Bean
    public UserService userService(final UserDao userDao, final EntityMapper entityMapper) {
        return new UserService(userDao, entityMapper);
    }

}
