package com.chill.table.football.application.matches.dto.in;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class EndMatchRequestDTO {
    private Long matchId;
    private Long teamId;
    private LocalDateTime endDateTime;
}
