package com.chill.table.football.application.matchesfinder;

import com.chill.table.football.application.matchesfinder.projection.MatchDateTimeProjection;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class MatchesFinder {

    private MatchesFinderRepository matchesFinderRepository;

    public MatchesFinder(MatchesFinderRepository matchesFinderRepository) {
        this.matchesFinderRepository = Objects.requireNonNull(matchesFinderRepository);
    }

    public Optional<MatchDateTimeProjection> findMatchIn30MinutesBefore(LocalDateTime dateTime) {
        return matchesFinderRepository.findByDateTimeBetween(dateTime.minusMinutes(30), dateTime);
    }
}
