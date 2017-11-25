package com.chill.table.football.infrastructure.authentication;

import com.chill.table.football.application.user.Player;
import com.chill.table.football.application.user.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Autowired
    public UserAuthService(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Player player = playerRepository.findByUserName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));

        return new User(
                player.getUserName(),
                player.getPassword(),
                AuthorityUtils.createAuthorityList());
    }
}