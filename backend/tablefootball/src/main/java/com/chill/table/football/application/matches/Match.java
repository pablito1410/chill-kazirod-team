package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.out.CreateMatchWithPlayersResponseDTO;
import com.chill.table.football.application.matches.exception.MatchDoesNotContainTeam;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @JoinColumn(name = "HOME")
    @OneToOne(cascade = CascadeType.ALL)
    private Team firstTeam;

    @JoinColumn(name = "GUESTS")
    @OneToOne(cascade = CascadeType.ALL)
    private Team secondTeam;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private Set<Acceptation> acceptations;

    private Match() {
        // dla hibernate
    }

    Match(LocalDateTime dateTime, Team firstTeam, Team secondTeam) {
        this.creationDateTime = LocalDateTime.now();
        this.dateTime = dateTime;
        this.state = State.CREATED;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    CreateMatchWithPlayersResponseDTO toCreateMatchResponseDTO() {
        return CreateMatchWithPlayersResponseDTO.builder()
                .matchId(id)
                .firstTeamId(firstTeam.getId())
                .secondTeamId(secondTeam.getId())
                .state(state.name())
                .build();
    }

    Match end(Team winningTeam) {
        if (!firstTeam.equals(winningTeam) || !secondTeam.equals(winningTeam)) {
            throw new MatchDoesNotContainTeam(id , winningTeam.getId());
        }
        this.endDateTime = LocalDateTime.now();
        return this;
    }

    enum State {
        CREATED, ACCEPTED, STARTED, ENDED;
    }
}
