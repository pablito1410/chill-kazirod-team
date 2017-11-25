package com.chill.table.football.application.user;

import com.chill.table.football.application.matches.exception.PlayerAlreadyExistException;
import com.chill.table.football.application.matches.exception.PlayerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {

    Optional<Player> findById(Long id);

    Player save(Player player);

    Optional<Player> findByUserName(String userName);

    default Player findByIdOrThrow(Long playerId) {
       return findById(playerId)
               .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    default Player findByUserNameOrThrow(String userName) {
        return findByUserName(userName)
                .orElseThrow(() -> new PlayerAlreadyExistException(userName));
    }

}
