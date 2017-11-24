package com.chill.table.football.application.matches.dto.in;

import lombok.Value;

@Value
public class SetWinnerRequestDTO {
    private Long matchId;
    private Long teamId;
}
