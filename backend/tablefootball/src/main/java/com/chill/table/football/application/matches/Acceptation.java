package com.chill.table.football.application.matches;


import javax.persistence.*;

@Entity
public class Acceptation {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Player player;

    @OneToOne
    private Match match;

    @Enumerated(EnumType.STRING)
    private State state;

    Acceptation(Player player, Match match) {
        this.player = player;
        this.match = match;
        this.state = State.PADDING;
    }

    Acceptation accept() {
        this.state = State.ACCEPTED;
        return this;
    }

    Acceptation reject() {
        this.state = State.REJECTED;
        return this;
    }

    // TODO jakiś task oznaczający po rozpoczęciu meczu
    Acceptation markTimedOut() {
        this.state = State.TIMED_OUT;
        return this;
    }

    public Long getId() {
        return id;
    }

    boolean isAccepted() {
        return state == State.ACCEPTED;
    }

    boolean tryAccept(Acceptation toAccept) {
        if (this.equals(toAccept)) {
            state = State.ACCEPTED;
            return true;
        }
        return false;
    }

    boolean tryReject(Acceptation toReject) {
        if (this.equals(toReject)) {
            state = State.REJECTED;
            return true;
        }
        return false;
    }

    void timeoutIfNeverReacted() {
        if (state == State.PADDING) {
            markTimedOut();
        }
    }

    public enum State {
        PADDING, ACCEPTED, REJECTED, TIMED_OUT;
    }
}
