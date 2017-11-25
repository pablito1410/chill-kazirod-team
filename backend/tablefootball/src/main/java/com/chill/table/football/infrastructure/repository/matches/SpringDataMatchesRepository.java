package com.chill.table.football.infrastructure.repository.matches;

import com.chill.table.football.application.matches.Match;
import com.chill.table.football.application.matches.MatchesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SpringDataMatchesRepository extends JpaRepository<Match, Long>, MatchesRepository {

}
