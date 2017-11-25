package com.chill.table.football.infrastructure.springconfig;

import com.chill.table.football.application.query.statistics.StatisticsDao;
import com.chill.table.football.infrastructure.repository.statistics.StatisticsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class StatisticsConfig {

    @Bean
    StatisticsDao statisticsDao(final JdbcTemplate jdbc) {
        return new StatisticsRepository(jdbc);
    }
}
