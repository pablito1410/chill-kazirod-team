package com.chill.table.football.application.query.player;

import com.chill.table.football.application.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerFinderRepository extends JpaRepository<Player, Long> {

    Optional<PlayerProjection> findById(Long playerId);

    List<PlayerProjection> findBy();
}
