package com.chill.table.football.infrastructure.repository.matches;

import com.chill.table.football.application.matches.Acceptation;
import com.chill.table.football.application.matches.MatchesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAcceptationRepository extends JpaRepository<Acceptation, Long>, MatchesRepository.AcceptationRepository {
}
