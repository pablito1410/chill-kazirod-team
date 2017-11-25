package com.chill.table.football.application.query.acceptation;


import org.springframework.jdbc.core.JdbcOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AcceptationFinderRepository {

    private static final String SQL_SELECT_MATCH_IDS_BY_PLAYER_ID =
            "SELECT m.id FROM Acceptation a " +
                    "JOIN Player p ON a.player_id = p.player_id " +
                    "JOIN Match m ON a.player_id = m.player_id " +
                    "WHERE p.player_id = ?";

    private JdbcOperations jdbcOperations;

    public AcceptationFinderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = Objects.requireNonNull(jdbcOperations);
    }

    List<AcceptationProjection> findByPlayerId(Long playerId) {
        ArrayList<AcceptationProjection> output = new ArrayList<>();
        List<AcceptationProjectionImpl> acceptationProjections = jdbcOperations.queryForList(
                SQL_SELECT_MATCH_IDS_BY_PLAYER_ID, new Object[]{playerId}, AcceptationProjectionImpl.class);
        output.addAll(acceptationProjections);
        return output;

    }
}
