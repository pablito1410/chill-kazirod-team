package com.chill.table.football.application.matchesfinder.projection.impl;

import com.chill.table.football.application.matchesfinder.projection.MatchProjection;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

@Value
public class MatchProjectionImpl implements MatchProjection {
    private Long id;

    private String state;

    private TeamProjection firstTeam;

    private TeamProjection secondTeam;

    public MatchProjectionImpl(MatchProjection matchProjection) {
        this.id = matchProjection.getId();
        this.state = matchProjection.getState();
        this.firstTeam = new TeamProjectionImpl(matchProjection.getFirstTeam());
        this.secondTeam = new TeamProjectionImpl(matchProjection.getSecondTeam());
    }

    @Value
    class TeamProjectionImpl implements TeamProjection {
        private Long id;
        private Set<PlayerProjection> players;

        TeamProjectionImpl(TeamProjection teamProjection) {
            this.id = teamProjection.getId();
            this.players = teamProjection.getPlayers().stream().map(PlayerProjectionImpl::new).collect(Collectors.toSet());
        }

        @Value
        class PlayerProjectionImpl implements PlayerProjection {
            private Long id;

            PlayerProjectionImpl(PlayerProjection playerProjection) {
                this.id = playerProjection.getId();
            }
        }
    }
}
