package com.chill.table.football.infrastructure.repository.matches;

import com.chill.table.football.application.matches.MatchesRepository;
import com.chill.table.football.application.matches.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTeamRepository extends JpaRepository<Team, Long>, MatchesRepository.TeamRepository {

}
