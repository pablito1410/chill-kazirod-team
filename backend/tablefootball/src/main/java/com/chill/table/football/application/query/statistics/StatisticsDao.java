package com.chill.table.football.application.query.statistics;

import com.chill.table.football.application.query.player.PlayerProjection;

import java.util.Map;

public interface StatisticsDao {

    Map<PlayerProjection, Long> getPlayerWinners();
}
