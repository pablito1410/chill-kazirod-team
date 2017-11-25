package com.chill.table.football.application.query.matches;

import com.chill.table.football.application.matches.Match;
import com.chill.table.football.application.matches.exception.MatchNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

// Tworzenie objektów "impl" ma na celu obejście problemu ObjectMappera z serializowaniem objektów "Proxy" klas wygenerowanych przez springa
public class MatchesFinder {

    private MatchesFinderRepository matchesFinderRepository;

    public MatchesFinder(MatchesFinderRepository matchesFinderRepository) {
        this.matchesFinderRepository = Objects.requireNonNull(matchesFinderRepository);
    }

    public Optional<MatchDateTimeProjection> findMatchIn30MinutesBefore(LocalDateTime dateTime) {
        return matchesFinderRepository.findByDateTimeBetween(dateTime.minusMinutes(30), dateTime)
                .map(MatchDateTimeProjectionImpl::new);
    }

    public Optional<MatchProjection> findById(Long matchId) {
        return matchesFinderRepository.findById(matchId)
                .map(MatchProjectionImpl::new);
    }

    public List<MatchProjection> findAll() {
        return matchesFinderRepository.findBy()
                .stream()
                .map(MatchProjectionImpl::new)
                .collect(Collectors.toList());
    }

    public MatchProjection findOrThrow(Long matchId) {
        return findById(matchId)
            .orElseThrow(() -> new MatchNotFoundException("match with id = " + matchId + " does not exist"));
    }

}
