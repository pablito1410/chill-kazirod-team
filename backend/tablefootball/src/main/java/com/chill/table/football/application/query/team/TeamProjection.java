package com.chill.table.football.application.query.team;

import com.chill.table.football.application.query.player.PlayerProjection;

import java.util.Set;

public interface TeamProjection {
    Long getId();
    Set<PlayerProjection> getPlayers();
}
