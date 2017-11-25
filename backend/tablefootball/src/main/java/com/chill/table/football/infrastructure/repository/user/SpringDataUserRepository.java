package com.chill.table.football.infrastructure.repository.user;

import com.chill.table.football.application.user.Player;
import com.chill.table.football.application.user.PlayerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<Player, Long>, PlayerRepository {
}
