package com.chill.table.football.application.query.statistics;

import com.chill.table.football.application.query.player.PlayerProjection;

import java.util.Map;

public class StatisticsFinder {

    private final StatisticsDao statisticsDao;

    public StatisticsFinder(final StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    public Map<PlayerProjection, Long> getPlayersRank() {
        return statisticsDao.getPlayerWinners();
    }
}
