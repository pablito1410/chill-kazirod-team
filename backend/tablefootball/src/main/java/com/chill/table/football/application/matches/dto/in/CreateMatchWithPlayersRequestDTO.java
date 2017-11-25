package com.chill.table.football.application.matches.dto.in;

import com.chill.table.football.application.matches.dto.out.CreateMatchWithPlayersResponseDTO;
import com.chill.table.football.architecture.cqrs.Command;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Value
public class CreateMatchWithPlayersRequestDTO implements Command<CreateMatchWithPlayersResponseDTO> {

    @NotNull
    private Team firstTeam;

    @NotNull
    private Team secondTeam;

    @NotNull
    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Value
    public static class Team {
        @NotEmpty
        private String name;
        @NotNull
        private Long firstPlayer;
        @NotNull
        private Long secondPlayer;
    }
}
