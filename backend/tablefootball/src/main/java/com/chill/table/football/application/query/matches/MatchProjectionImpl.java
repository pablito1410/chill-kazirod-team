package com.chill.table.football.application.query.matches;

import com.chill.table.football.application.query.acceptation.AcceptationMatchProjection;
import com.chill.table.football.application.query.acceptation.AcceptationMatchProjectionImpl;
import com.chill.table.football.application.query.acceptation.AcceptationProjection;
import com.chill.table.football.application.query.team.TeamProjection;
import com.chill.table.football.application.query.team.TeamProjectionImpl;
import lombok.Value;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Value
class MatchProjectionImpl implements MatchProjection {
    private Long id;
    private String state;
    private TeamProjection firstTeam;
    private TeamProjection secondTeam;
    private Integer firstScore;
    private Integer secondScore;
    private Set<AcceptationMatchProjection> acceptations;

    MatchProjectionImpl(MatchProjection matchProjection) {
        this.id = matchProjection.getId();
        this.state = matchProjection.getState();
        this.firstTeam = new TeamProjectionImpl(matchProjection.getFirstTeam());
        this.secondTeam = new TeamProjectionImpl(matchProjection.getSecondTeam());
        this.firstScore = matchProjection.getFirstScore();
        this.secondScore = matchProjection.getSecondScore();
        this.acceptations = matchProjection.getAcceptations().stream()
                .map(AcceptationMatchProjectionImpl::new)
                .collect(Collectors.toSet());
    }


}
