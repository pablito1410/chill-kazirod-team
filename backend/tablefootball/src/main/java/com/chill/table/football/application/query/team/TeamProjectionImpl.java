package com.chill.table.football.application.query.team;

import com.chill.table.football.application.query.player.PlayerProjection;
import com.chill.table.football.application.query.player.PlayerProjectionImpl;
import lombok.Value;


@Value
public class TeamProjectionImpl implements TeamProjection {
    private Long id;
    private String name;
    private PlayerProjection firstPlayer;
    private PlayerProjection secondPlayer;

    public TeamProjectionImpl(TeamProjection teamProjection) {
        this.id = teamProjection.getId();
        this.name = teamProjection.getName();
        this.firstPlayer = new PlayerProjectionImpl(teamProjection.getFirstPlayer());
        this.secondPlayer = new PlayerProjectionImpl(teamProjection.getSecondPlayer());
    }
}
