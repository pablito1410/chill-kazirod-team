package com.chill.table.football.application.query.user;

import com.chill.table.football.application.user.User;

import java.util.List;
import java.util.Optional;

public interface UserFinderDao {

    Optional<User> getUser(long id);

    List<User> getAll();

    User getUser(String userName);
}
