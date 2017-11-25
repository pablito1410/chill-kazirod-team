package com.chill.table.football.application.query.player;

import com.chill.table.football.application.matches.exception.PlayerNotFoundException;
import com.chill.table.football.application.user.Player;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerFinder {
    private final PlayerFinderRepository playerFinderRepository;

    public PlayerFinder(PlayerFinderRepository playerFinderRepository) {
        this.playerFinderRepository = Objects.requireNonNull(playerFinderRepository);
    }

    public Optional<PlayerProjection> findById(Long playerId) {
        return playerFinderRepository.findById(playerId);
    }

    public PlayerProjection findByIdOrThrow(Long playerId) {
        return findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    public List<PlayerProjection> findAll() {
        return playerFinderRepository.findBy().stream()
                .map(PlayerProjectionImpl::new)
                .collect(Collectors.toList());
    }

}

