package com.chill.table.football.application.query.player;

import lombok.Value;

@Value
public class PlayerProjectionImpl implements PlayerProjection {
    private Long id;
    private String name;

    public PlayerProjectionImpl(PlayerProjection playerProjection) {
        this.id = playerProjection.getId();
        this.name = playerProjection.getName();
    }
}
