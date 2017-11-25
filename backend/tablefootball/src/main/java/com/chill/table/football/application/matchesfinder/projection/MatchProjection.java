package com.chill.table.football.application.matchesfinder.projection;

import java.util.Set;

public interface MatchProjection {

    Long getId();
    String getState();
    TeamProjection getFirstTeam();
    TeamProjection getSecondTeam();

    interface TeamProjection {
        Long getId();
        Set<PlayerProjection> getPlayers();

        interface PlayerProjection {
            Long getId();
        }
    }
}
