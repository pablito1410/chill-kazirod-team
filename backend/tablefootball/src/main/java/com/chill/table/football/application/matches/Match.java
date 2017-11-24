package com.chill.table.football.application.matches;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Match {

    @Id
    private Long id;

    private LocalDateTime beginDateTime;

    @Enumerated(EnumType.STRING)
    private State state;

    private Team firstTeam;

    private Team secondTeam;

    Match(LocalDateTime beginDateTime, State state) {
        this.beginDateTime = beginDateTime;
        this.state = state;
    }
}
