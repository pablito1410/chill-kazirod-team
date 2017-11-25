package com.chill.table.football.application.matches;

import java.util.Optional;
import java.util.Set;

public interface MatchesRepository {
    Match save(Match match);

    Optional<Match> findById(Long matchId);

    interface PlayerRepository {
        Optional<Player> findById(Long playerId);
    }

    interface TeamRepository {
        Optional<Team> findTop1ByIdIn(Set<Long> playerIds);

        Optional<Team> findById(Long teamId);
    }
}
