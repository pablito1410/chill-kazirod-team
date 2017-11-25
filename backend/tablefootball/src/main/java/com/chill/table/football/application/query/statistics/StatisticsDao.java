package com.chill.table.football.application.query.statistics;

import com.chill.table.football.application.user.ports.outgoing.UserDTO;

import java.util.Map;

public interface StatisticsDao {

    Map<UserDTO, Long> getPlayerWinners();
}
