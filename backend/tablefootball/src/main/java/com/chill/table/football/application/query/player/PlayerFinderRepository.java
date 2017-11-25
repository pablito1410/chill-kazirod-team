package com.chill.table.football.application.query.player;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PlayerFinderRepository {

    private static final String SQL_SELECT_PLAYER_BY_ID =
            "SELECT p.id, u.name " +
                    "FROM Player p " +
                    "JOIN User u ON u.id = p.id " +
                    "WHERE p.id = ? ";

    private static final String SQL_SELECT_PLAYER =
            "SELECT p.id, u.name " +
                    "FROM Player p " +
                    "JOIN User u ON u.id = p.id ";

    private JdbcOperations jdbcOperations;

    public PlayerFinderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = Objects.requireNonNull(jdbcOperations);
    }

    Optional<PlayerProjection> findById(Long playerId) {
        return Optional.ofNullable(
                jdbcOperations.queryForObject(SQL_SELECT_PLAYER_BY_ID, new BeanPropertyRowMapper<>(), playerId));
    }

    List<PlayerProjection> findBy() {
        List<PlayerProjection> output = new ArrayList<>();
        List<PlayerProjectionImpl> playerProjections = jdbcOperations.queryForList(SQL_SELECT_PLAYER, PlayerProjectionImpl.class);
        output.addAll(playerProjections);
        return output;
    }
}
