package com.chill.table.football.application.user;

import com.chill.table.football.application.exception.NotFoundException;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import com.chill.table.football.application.user.ports.outgoing.UserDao;
import com.chill.table.football.application.util.EntityMapper;
import org.modelmapper.TypeToken;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UserService {

    private final UserDao userDao;
    private final EntityMapper entityMapper;

    public UserService(final UserDao userDao,
                       final EntityMapper entityMapper) {
        this.userDao = userDao;
        this.entityMapper = entityMapper;
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
        return entityMapper.map(user, UserDTO.class);
    }

    public Collection<UserDTO> getAll() {
        final List<User> users = userDao.getAll();
        return entityMapper.mapCollection(users, UserDTO.class, new TypeToken<HashSet<UserDTO>>(){} .getType());
    }
}
