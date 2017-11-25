package com.chill.table.football.application.user;

import com.chill.table.football.application.user.exception.UserNotFoundException;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;

public class UserService {

    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public Long handle(final CreateUserCommand command) {
        final User user = new User(command.getUserName(), command.getPassword());
        userDao.save(user);
        return user.getId();
    }

    public User getUserByName(final String userName) {
        return userDao.getUser(userName)
                .orElseThrow(UserNotFoundException::new);
    }
}
