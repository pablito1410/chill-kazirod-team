package com.chill.table.football.application.query.acceptation;

import com.chill.table.football.application.matches.Acceptation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptationFinderRepository extends JpaRepository<Acceptation, Long> {
    List<AcceptationProjection> findByPlayerId(Long playerId);
}
