package com.chill.table.football.application.query.matches;

import com.chill.table.football.application.query.acceptation.AcceptationMatchProjection;
import com.chill.table.football.application.query.team.TeamProjection;

import java.util.Set;

public interface MatchProjection {
    Long getId();
    String getState();
    TeamProjection getFirstTeam();
    TeamProjection getSecondTeam();
    Integer getFirstScore();
    Integer getSecondScore();
    Set<AcceptationMatchProjection> getAcceptations();
}
