package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime beginDateTime;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @JoinColumn(name = "HOME")
    @OneToOne(cascade = CascadeType.ALL)
    private Team home;

    @JoinColumn(name = "GUESTS")
    @OneToOne(cascade = CascadeType.ALL)
    private Team guests;

    Match(LocalDateTime dateTime, Team firstTeam, Team secondTeam) {
        this.beginDateTime = LocalDateTime.now();
        this.dateTime = dateTime;
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
