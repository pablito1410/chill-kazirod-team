package com.chill.table.football.application.user;

import com.chill.table.football.application.exception.NotFoundException;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.chill.table.football.application.user.ports.outgoing.UserDao;

import java.util.Collection;

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

    public User getUser(final long id) {
        return userDao
                .getUser(id)
                .orElseThrow(NotFoundException::new);
    }

    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
