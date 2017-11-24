package com.chill.table.football.application.matches.dto.in;

import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Value
public class CreateMatchRequestDTO {

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
        private Set<Long> players;    // TODO rename players
    }
}
