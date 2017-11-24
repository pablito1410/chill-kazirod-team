package com.chill.table.football.application.matches;

import java.util.Optional;

public interface PlayerRepository {
    Optional<Player> findById(Long playerId);
}
