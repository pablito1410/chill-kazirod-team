package com.chill.table.football.application.user;

import com.chill.table.football.application.user.exception.UserAlreadyExistException;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;

import java.util.Optional;

public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserDao userDao, final PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public Long handle(final CreateUserCommand command) {
        Optional.ofNullable(userDao.getUser(command.getUserName()))
                .ifPresent(u -> {
                    throw new UserAlreadyExistException(
                            String.format("User with username=%s already exist", command.getUserName()));
                });

        final String rawPassword = command.getPassword();
        final String hash = passwordEncoder.encode(rawPassword);
        final User user = new User(command.getUserName(), hash);
        userDao.save(user);
        return user.getId();
    }

}
