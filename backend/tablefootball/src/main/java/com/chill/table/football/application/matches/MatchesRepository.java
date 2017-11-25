package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.exception.AcceptationNotFoundException;
import com.chill.table.football.application.matches.exception.MatchNotFoundException;
import com.chill.table.football.application.matches.exception.PlayerNotFoundException;
import com.chill.table.football.application.matches.exception.TeamNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchesRepository {
    Match save(Match match);

    Optional<Match> findById(Long matchId);

    default Match findByIdOrThrow(Long matchId) {
        return findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException(matchId));
    }

    List<Match> findAll();

    interface PlayerRepository {
        Optional<Player> findById(Long playerId);

        default Player findByIdOrThrow(Long playerId) {
            return findById(playerId)
                    .orElseThrow(() -> new PlayerNotFoundException(playerId));
        }
    }

    interface TeamRepository {
        Optional<Team> findByFirstPlayerIdAndSecondPlayerId(Long firstPlayer, Long secondPlayer);

        Optional<Team> findById(Long teamId);

        default Team findByIdOrThrow(Long teamId) {
            return findById(teamId)
                    .orElseThrow(() -> new TeamNotFoundException(teamId, null));
        }

        Optional<Team> findByName(String name);
    }

    interface AcceptationRepository extends JpaRepository<Acceptation, Long> {
        Optional<Acceptation> findById(Long acceptationId);

        default Acceptation findByIdOrThrow(Long acceptationId) {
            return findById(acceptationId)
                    .orElseThrow(() -> new AcceptationNotFoundException(acceptationId));
        }
    }
}
