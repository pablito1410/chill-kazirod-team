package com.chill.table.football.infrastructure.authentication;

import com.chill.table.football.application.user.User;
import com.chill.table.football.application.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {


    private final UserDao userDao;

    @Autowired
    public UserAuthService(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.getUser(name).orElseThrow(() -> {
            return new UsernameNotFoundException(String.format("User with name=%s was not found", name));
        });
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList());
    }
}