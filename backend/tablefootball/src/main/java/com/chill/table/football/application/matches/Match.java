package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;
import com.chill.table.football.application.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Match {

    @Id
    private Long id;

    private LocalDateTime beginDateTime;

    @Enumerated(EnumType.STRING)
    private State state;

    private Team firstTeam;

    private Team secondTeam;

    Match(Team firstTeam, Team secondTeam) {
        this.beginDateTime = LocalDateTime.now();
        this.state = State.CREATED;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    CreateMatchResponseDTO toCreateMatchResponseDTO() {
        return CreateMatchResponseDTO.builder()
                .matchId(id)
                .firstTeamId(firstTeam.getId())
                .secondTeamId(secondTeam.getId())
                .state(state.name())
                .build();
    }
}
