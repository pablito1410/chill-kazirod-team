package com.chill.table.football.application.matches.dto;

import lombok.Value;

@Value
public class SetWinnerDTO {
    private Long matchId;
    private Long teamId;
}
