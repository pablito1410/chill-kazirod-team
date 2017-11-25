package com.chill.table.football.application.matches.dto.in;

import com.chill.table.football.application.matches.dto.out.EndMatchResponseDTO;
import com.chill.table.football.architecture.cqrs.Command;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class EndMatchRequestDTO implements Command<EndMatchResponseDTO> {
    private Long matchId;
    private Long teamId;
    private Integer winnerScore;
    private Integer loserScore;
    private LocalDateTime endDateTime;
}
