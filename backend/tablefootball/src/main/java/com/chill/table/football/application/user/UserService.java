package com.chill.table.football.application.user;

import com.chill.table.football.application.exception.NotFoundException;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import com.chill.table.football.application.user.ports.outgoing.UserDao;
import com.chill.table.football.application.util.EntityMapper;

import java.util.Collection;

public class UserService {

    private final UserDao userDao;
    private final EntityMapper modelMapper;

    public UserService(final UserDao userDao,
                       final EntityMapper entityMapper) {
        this.userDao = userDao;
        this.modelMapper = entityMapper;
    }

    public Long handle(final CreateUserCommand command) {
        final User user = new User(command.getUserName(), command.getPassword());
        userDao.save(user);
        return user.getId();
    }

    public UserDTO getUser(final long id) {
        final User user = userDao
                .getUser(id)
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(user, UserDTO.class);
    }

    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
