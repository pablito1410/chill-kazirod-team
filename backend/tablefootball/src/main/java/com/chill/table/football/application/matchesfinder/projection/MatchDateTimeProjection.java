package com.chill.table.football.application.matchesfinder.projection;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Value;

import java.time.LocalDateTime;

public interface MatchDateTimeProjection {
    LocalDateTime getDateTime();
}
