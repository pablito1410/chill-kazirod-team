package com.chill.table.football.application.query.statistics;

import com.chill.table.football.application.user.ports.outgoing.UserDTO;

import java.util.Map;

public class StatisticsFinder {


    private final StatisticsDao statisticsDao;

    public StatisticsFinder(final StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    public Map<UserDTO, Long> getPlayersRank() {
        return statisticsDao.getPlayerWinners();
    }
}
