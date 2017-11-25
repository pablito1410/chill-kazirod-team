package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.query.acceptation.AcceptationFinder;
import com.chill.table.football.application.query.acceptation.AcceptationFinderRepository;
import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.query.matches.MatchesFinderRepository;
import com.chill.table.football.application.query.player.PlayerFinder;
import com.chill.table.football.application.query.player.PlayerFinderRepository;
import com.chill.table.football.application.query.statistics.StatisticsDao;
import com.chill.table.football.application.query.statistics.StatisticsFinder;
import com.chill.table.football.application.query.team.TeamFinder;
import com.chill.table.football.application.query.team.TeamFinderRepository;
import com.chill.table.football.application.query.user.UserFinder;
import com.chill.table.football.application.query.user.UserFinderDao;
import com.chill.table.football.application.util.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

@Configuration
class FinderConfiguration {

    @Bean
    MatchesFinder matchesFinder(MatchesFinderRepository matchesFinderRepository) {
        return new MatchesFinder(matchesFinderRepository);
    }

    @Bean
    TeamFinder teamFinder(TeamFinderRepository teamFinderRepository) {
        return new TeamFinder(teamFinderRepository);
    }

    @Bean
    PlayerFinder playerFinder(PlayerFinderRepository playerFinderRepository) {
        return new PlayerFinder(playerFinderRepository);
    }

    @Bean
    UserFinder userFinder(final UserFinderDao userFinderDao, final EntityMapper entityMapper) {
        return new UserFinder(userFinderDao, entityMapper);
    }

    @Bean
    AcceptationFinder acceptationFinder(AcceptationFinderRepository acceptationFinderRepository) {
        return new AcceptationFinder(acceptationFinderRepository);
    }

    @Bean
    StatisticsFinder statisticsFinder(final StatisticsDao statisticsDao) {
        return new StatisticsFinder(statisticsDao);
    }

    @Bean
    AcceptationFinderRepository acceptationFinderRepository(JdbcOperations jdbcOperations) {
        return new AcceptationFinderRepository(jdbcOperations);
    }
}
