package com.chill.table.football.application.query.team;

import com.chill.table.football.application.matches.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamFinderRepository extends JpaRepository<Team, Long> {
    Optional<TeamProjection> findById(Long teamId);

    List<TeamProjection> findBy();

    Optional<TeamProjection> findByName(String name);
}
