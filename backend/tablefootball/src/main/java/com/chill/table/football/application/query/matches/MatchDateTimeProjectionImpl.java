package com.chill.table.football.application.query.matches;

import lombok.Value;

import java.time.LocalDateTime;

@Value
class MatchDateTimeProjectionImpl implements MatchDateTimeProjection {
    private LocalDateTime dateTime;

    MatchDateTimeProjectionImpl(MatchDateTimeProjection matchDateTimeProjection) {
        this.dateTime = matchDateTimeProjection.getDateTime();
    }
}
