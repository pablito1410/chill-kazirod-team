package com.chill.table.football.application.query.matches;

import com.chill.table.football.application.matches.Match;
import com.chill.table.football.application.query.matches.MatchDateTimeProjection;
import com.chill.table.football.application.query.matches.MatchProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchesFinderRepository extends JpaRepository<Match, Long> {

    Optional<MatchDateTimeProjection> findByDateTimeBetween(LocalDateTime after, LocalDateTime before);

    Optional<MatchProjection> findById(Long matchId);

    List<MatchProjection> findBy();
}
