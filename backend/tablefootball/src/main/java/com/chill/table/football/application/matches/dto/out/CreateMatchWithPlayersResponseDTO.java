package com.chill.table.football.application.matches.dto.out;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateMatchWithPlayersResponseDTO {
    private Long matchId;
    private Long firstTeamId;
    private Long secondTeamId;
    private String state;
}
