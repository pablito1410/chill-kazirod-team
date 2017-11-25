package com.chill.table.football.infrastructure.repository.statistics;

import com.chill.table.football.application.query.player.PlayerProjection;
import com.chill.table.football.application.query.statistics.StatisticsDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class StatisticsRepository implements StatisticsDao {

    private final JdbcTemplate jdbcTemplate;

    public StatisticsRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Map<PlayerProjection, Long> getPlayerWinners() {
//        final Map<UserDTO, Long> winners = new HashMap<>();
//        jdbcTemplate.queryForMap("select\n" +
//                        "\t\tp.id,\n" +
//                        "\t\tu.user_name,\n" +
//                        "\t\t(select count(*) \n" +
//                        "\t\t\tfrom match m\n" +
//                        "\t\t\tinner join team t on t.id = m.winner_id\n" +
//                        "\t\t\twhere t.first_player_id = p.id or t.second_player_id = p.id) as winners\n" +
//                        "\t\tfrom player p\n" +
//                        "\t\tinner join users u on u.user_id = p.user_id",
//                new RowMapper<Long>() {
//                    @Override
//                    public Long mapRow(final ResultSet rs, final int i) throws SQLException {
//                        final UserDTO user = new UserDTO(
//                                rs.getLong("user_id"),
//                                rs.getString("user_name"));
//                        winners.put(user ,rs.getLong("winners"));
//                        return null;
//                    }
//                });
//        return winners;
        return null;
    }
}
