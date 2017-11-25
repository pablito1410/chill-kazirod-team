package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.query.statistics.StatisticsFinder;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController()
@RequestMapping("api/statistics")
public class StatisticsController {

    private final StatisticsFinder statisticsFinder;

    @Autowired
    public StatisticsController(final StatisticsFinder statisticsFinder) {
        this.statisticsFinder = statisticsFinder;
    }

    @RequestMapping(path = "/rank", method = RequestMethod.GET)
    public ResponseEntity<Map<UserDTO, Long>> getRank() {
        final Map<UserDTO, Long> playersRank = statisticsFinder.getPlayersRank();
        return new ResponseEntity<>(playersRank, HttpStatus.OK);
    }
}
