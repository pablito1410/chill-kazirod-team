package com.chill.table.football.application.user;

import com.chill.table.football.application.user.exception.UserNotFoundException;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import com.chill.table.football.application.util.EntityMapper;
import org.modelmapper.TypeToken;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UserFinder {

    private final UserDao userDao;
    private final EntityMapper entityMapper;

    public UserFinder(final UserDao userDao,
                      final EntityMapper entityMapper) {
        this.userDao = userDao;
        this.entityMapper = entityMapper;
    }

    public UserDTO getUser(final long id) {
        final User user = userDao
                .getUser(id)
                .orElseThrow(UserNotFoundException::new);
        return entityMapper.map(user, UserDTO.class);
    }

    public Collection<UserDTO> getAll() {
        final List<User> users = userDao.getAll();
        return entityMapper.mapCollection(users, UserDTO.class, new TypeToken<HashSet<UserDTO>>(){}.getType());
    }
}
