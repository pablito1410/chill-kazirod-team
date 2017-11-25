package com.chill.table.football.application.matchesfinder.projection.impl;

import com.chill.table.football.application.matchesfinder.projection.MatchDateTimeProjection;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class MatchDateTimeProjectionImpl implements MatchDateTimeProjection {
    private LocalDateTime dateTime;
}
