package com.chill.table.football.application.query.player;

import java.util.Objects;

public class PlayerFinder {
    private final PlayerFinderRepository playerFinderRepository;

    public PlayerFinder(PlayerFinderRepository playerFinderRepository) {
        this.playerFinderRepository = Objects.requireNonNull(playerFinderRepository);
    }
}
