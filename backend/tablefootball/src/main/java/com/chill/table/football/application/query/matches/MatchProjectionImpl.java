package com.chill.table.football.application.query.matches;

import com.chill.table.football.application.query.team.TeamProjection;
import com.chill.table.football.application.query.team.TeamProjectionImpl;
import lombok.Value;

@Value
class MatchProjectionImpl implements MatchProjection {
    private Long id;

    private String state;

    private TeamProjection firstTeam;

    private TeamProjection secondTeam;

    MatchProjectionImpl(MatchProjection matchProjection) {
        this.id = matchProjection.getId();
        this.state = matchProjection.getState();
        this.firstTeam = new TeamProjectionImpl(matchProjection.getFirstTeam());
        this.secondTeam = new TeamProjectionImpl(matchProjection.getSecondTeam());
    }


}
