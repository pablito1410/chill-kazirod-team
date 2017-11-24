package com.chill.table.football.application.matchesfinder;

import com.chill.table.football.application.matches.Match;
import com.chill.table.football.application.matchesfinder.projection.MatchDateTimeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MatchesFinderRepository extends JpaRepository<Match, Long> {

    Optional<MatchDateTimeProjection> findByDateTimeBetween(LocalDateTime gratherDateTime, LocalDateTime lessDateTime);
}
