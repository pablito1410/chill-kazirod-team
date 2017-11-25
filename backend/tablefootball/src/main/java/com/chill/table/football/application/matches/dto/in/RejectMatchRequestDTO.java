package com.chill.table.football.application.matches.dto.in;

import com.chill.table.football.application.matches.dto.out.AcceptMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.RejectMatchResponseDTO;
import com.chill.table.football.architecture.cqrs.Command;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class RejectMatchRequestDTO implements Command<RejectMatchResponseDTO> {
    @NotNull
    private Long matchId;
    @NotNull
    private Long acceptationId;
}
