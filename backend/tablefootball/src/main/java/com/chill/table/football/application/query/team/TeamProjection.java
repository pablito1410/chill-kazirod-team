package com.chill.table.football.application.query.team;

import com.chill.table.football.application.query.player.PlayerProjection;

public interface TeamProjection {
    Long getId();
    String getName();
    PlayerProjection getFirstPlayer();
    PlayerProjection getSecondPlayer();
}
