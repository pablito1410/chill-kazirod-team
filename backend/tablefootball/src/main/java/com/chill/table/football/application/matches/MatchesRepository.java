package com.chill.table.football.application.matches;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MatchesRepository {
    Match save(Match match);

    Optional<Match> findById(Long matchId);
}
