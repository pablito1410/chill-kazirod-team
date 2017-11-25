package com.chill.table.football.application.matches;


import javax.persistence.*;

// TODO jakieś daty?
// TODO przymyśleć jeszcze raz czy tak to ma działać
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

    private Acceptation accept() {
        this.state = State.ACCEPTED;
        return this;
    }

    private Acceptation reject() {
        this.state = State.REJECTED;
        return this;
    }

    // TODO jakiś task oznaczający po rozpoczęciu meczu
    private Acceptation markTimedOut() {
        this.state = State.TIMED_OUT;
        return this;
    }

    public enum State {
        PADDING, ACCEPTED, REJECTED, TIMED_OUT;
    }
}
