package com.chill.table.football.application.matches.dto.in;

import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Value
public class CreateMatchRequestDTO {

    @NotNull
    private Team firstTeam;

    @NotNull
    private Team secondTeam;

    @Value
    public static class Team {

        @NotEmpty
        private String name;

        @NotEmpty
        private Set<Long> users;
    }
}
