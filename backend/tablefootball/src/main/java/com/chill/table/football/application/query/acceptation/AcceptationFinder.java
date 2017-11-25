package com.chill.table.football.application.query.acceptation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AcceptationFinder {
    private AcceptationFinderRepository acceptationFinderRepository;

    public AcceptationFinder(AcceptationFinderRepository acceptationFinderRepository) {
        this.acceptationFinderRepository = Objects.requireNonNull(acceptationFinderRepository);
    }

    public List<AcceptationProjection> findById(Long playerId) {
        return acceptationFinderRepository.findByPlayerId(playerId);
    }
}
