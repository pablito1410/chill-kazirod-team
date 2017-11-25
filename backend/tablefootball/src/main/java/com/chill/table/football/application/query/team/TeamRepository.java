package com.chill.table.football.application.query.team;

import com.chill.table.football.application.matches.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
