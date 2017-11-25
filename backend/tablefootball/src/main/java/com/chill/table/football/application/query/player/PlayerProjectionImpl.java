package com.chill.table.football.application.query.player;

import lombok.Value;

@Value
public class PlayerProjectionImpl implements PlayerProjection {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;

    public PlayerProjectionImpl(PlayerProjection playerProjection) {
        this.id = playerProjection.getId();
        this.userName = playerProjection.getUserName();
        this.firstName = playerProjection.getFirstName();
        this.lastName = playerProjection.getLastName();
    }
}
