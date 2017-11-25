package com.chill.table.football.application.user;

import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.chill.table.football.application.user.ports.outgoing.UserDao;

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
}
