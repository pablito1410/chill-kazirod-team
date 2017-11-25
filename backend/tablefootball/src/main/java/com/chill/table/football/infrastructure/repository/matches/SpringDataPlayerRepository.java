package com.chill.table.football.infrastructure.repository.matches;

import com.chill.table.football.application.matches.MatchesRepository;
import com.chill.table.football.application.matches.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPlayerRepository extends JpaRepository<Player, Long>, MatchesRepository.PlayerRepository {
}
