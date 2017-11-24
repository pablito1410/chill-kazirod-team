package com.chill.table.football.application.matches;

import java.util.Optional;
import java.util.Set;

public interface TeamRepository {
    Optional<Team> findTop1ByIdIn(Set<Long> playerIds);
}
