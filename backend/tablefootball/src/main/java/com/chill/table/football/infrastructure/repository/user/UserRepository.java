package com.chill.table.football.infrastructure.repository.user;

import com.chill.table.football.application.user.User;
import com.chill.table.football.application.user.ports.outgoing.UserDao;

import java.util.Collection;
import java.util.Optional;

public class UserRepository implements UserDao {

    private final SpringDataUserRepository repository;

    public UserRepository(final SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getUser(final long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User save(final User user) {
        return repository.save(user);
    }
}
