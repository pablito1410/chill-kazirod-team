package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.out.CreateMatchWithPlayersResponseDTO;
import com.chill.table.football.application.matches.exception.AcceptationNotFoundInMatchException;
import com.chill.table.football.application.matches.exception.MatchDoesNotContainTeam;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Team firstTeam;

    @OneToOne(cascade = CascadeType.ALL)
    private Team secondTeam;

    private Integer firstScore;

    private Integer secondScore;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private Set<Acceptation> acceptations = new HashSet<>();

    private Match() {
        // dla hibernate
    }

    Match(LocalDateTime dateTime, Team firstTeam, Team secondTeam) {
        this.creationDateTime = LocalDateTime.now();
        this.dateTime = dateTime;
        this.state = State.CREATED;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.acceptations.addAll(firstTeam.createAcceptations(this));
        this.acceptations.addAll(secondTeam.createAcceptations(this));
    }

    CreateMatchWithPlayersResponseDTO toCreateMatchResponseDTO() {
        return CreateMatchWithPlayersResponseDTO.builder()
                .matchId(id)
                .firstTeamId(firstTeam.getId())
                .secondTeamId(secondTeam.getId())
                .state(state.name())
                .build();
    }

    Match end(Team winningTeam, Integer firstScore, Integer secondScore, LocalDateTime endDateTime) {
        if (!firstTeam.equals(winningTeam) || !secondTeam.equals(winningTeam)) {
            throw new MatchDoesNotContainTeam(id , winningTeam.getId());
        }
        this.state = State.FINISHED;
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.endDateTime = endDateTime;
        return this;
    }

    Match accept(Acceptation toAccept) {
        for (Acceptation acceptation : acceptations) {
            if (acceptation.tryAccept(toAccept)) {
                checkAllAccepted();
                return this;
            }
        }
        throw new AcceptationNotFoundInMatchException(toAccept.getId());
    }

    Match reject(Acceptation toReject) {
        for (Acceptation acceptation : acceptations) {
            if (acceptation.tryReject(toReject)) {
                setAcceptationProcessedStatuses();
                return this;
            }
        }
        throw new AcceptationNotFoundInMatchException(toReject.getId());
    }

    private void setAcceptationProcessedStatuses() {
        for (Acceptation acceptation : acceptations) {
            acceptation.timeoutIfNeverReacted();
        }
    }

    private void checkAllAccepted() {
        boolean allAccepted = true;
        for (Acceptation acceptation : acceptations) {
            if (!acceptation.isAccepted()) {
                allAccepted = false;
            }
        }
        if (allAccepted) {
            this.state = State.ACCEPTED;
        }
    }

    enum State {
        CREATED, ACCEPTED, CANCELED, FINISHED;
    }
}
