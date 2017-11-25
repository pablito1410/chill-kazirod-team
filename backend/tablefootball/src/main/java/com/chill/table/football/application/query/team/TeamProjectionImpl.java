package com.chill.table.football.application.query.team;

import com.chill.table.football.application.query.player.PlayerProjection;
import com.chill.table.football.application.query.player.PlayerProjectionImpl;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

@Value
public class TeamProjectionImpl implements TeamProjection {
    private Long id;
    private Set<PlayerProjection> players;

    public TeamProjectionImpl(TeamProjection teamProjection) {
        this.id = teamProjection.getId();
        this.players = teamProjection.getPlayers().stream()
                .map(PlayerProjectionImpl::new)
                .collect(Collectors.toSet());
    }
}
