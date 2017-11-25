package com.chill.table.football.infrastructure.repository.user;

import com.chill.table.football.application.query.user.UserFinderDao;
import com.chill.table.football.application.user.User;
import com.chill.table.football.application.user.UserDao;

import java.util.List;
import java.util.Optional;

public class UserRepository implements UserDao, UserFinderDao {

    private final SpringDataUserRepository repository;

    public UserRepository(final SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getUser(final long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User save(final User user) {
        return repository.save(user);
    }

    @Override
    public User getUser(final String userName) {
        return repository.findOneByUserName(userName);
    }
}
