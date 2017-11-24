package com.chill.table.football.application.user.ports.outgoing;

import com.chill.table.football.application.user.User;

import java.util.Collection;
import java.util.Optional;

public interface UserDao {

    Optional<User> getUser(long id);

    Collection<User> getAll();

    User save(User user);
}
