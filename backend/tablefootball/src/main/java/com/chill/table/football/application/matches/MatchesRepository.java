package com.chill.table.football.application.matches;

import java.util.Optional;

public interface MatchesRepository {
    Match save(Match match);

    Optional<Match> findById(Long matchId);
}
