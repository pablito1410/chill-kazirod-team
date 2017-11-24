package com.chill.table.football.infrastructure.repository;

import com.chill.table.football.application.matches.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMatchesRepository extends JpaRepository<Match, Long> {
}
