package com.chill.table.football.application.query.matches;

import com.chill.table.football.application.query.team.TeamProjection;

public interface MatchProjection {
    Long getId();
    String getState();
    TeamProjection getFirstTeam();
    TeamProjection getSecondTeam();
}
