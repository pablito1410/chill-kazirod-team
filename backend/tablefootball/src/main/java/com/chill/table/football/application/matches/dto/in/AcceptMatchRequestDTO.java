package com.chill.table.football.application.matches.dto.in;

import com.chill.table.football.application.matches.dto.out.AcceptMatchResponseDTO;
import com.chill.table.football.architecture.cqrs.Command;
import lombok.Value;

@Value
public class AcceptMatchRequestDTO implements Command<AcceptMatchResponseDTO> {
}
