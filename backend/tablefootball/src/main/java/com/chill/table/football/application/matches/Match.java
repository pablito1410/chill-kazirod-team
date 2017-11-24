package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MATCHES")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_ID")
    private Long id;

    @Column(name = "BEGIN_DATE_TIME", nullable = false)
    private LocalDateTime beginDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    private State state;

    private Team home;

    private Team guests;

    Match(Team firstTeam, Team secondTeam) {
        this.beginDateTime = LocalDateTime.now();
        this.state = State.CREATED;
        this.home = firstTeam;
        this.guests = secondTeam;
    }

    CreateMatchResponseDTO toCreateMatchResponseDTO() {
        return CreateMatchResponseDTO.builder()
                .matchId(id)
                .firstTeamId(home.getId())
                .secondTeamId(guests.getId())
                .state(state.name())
                .build();
    }
}
