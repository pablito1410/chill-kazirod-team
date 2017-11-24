package com.chill.table.football.infrastructure.repository.matches;

import com.chill.table.football.application.matches.Player;
import com.chill.table.football.application.matches.PlayerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPlayerRepository extends JpaRepository<Player, Long>, PlayerRepository {
}
