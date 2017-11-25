package com.chill.table.football.application.query.player;

import java.util.Objects;

public class PlayerFinder {
    private final PlayerRepository playerRepository;

    public PlayerFinder(PlayerRepository playerRepository) {
        this.playerRepository = Objects.requireNonNull(playerRepository);
    }
}
