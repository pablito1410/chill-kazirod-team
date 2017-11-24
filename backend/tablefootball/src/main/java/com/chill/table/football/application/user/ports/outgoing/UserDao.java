package com.chill.table.football.application.user.ports.outgoing;

import com.chill.table.football.application.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> getUser(long id);

    List<User> getAll();

    User save(User user);
}
