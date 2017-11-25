package com.chill.table.football.application.matches;

import com.chill.table.football.application.user.Player;
import com.google.common.collect.ImmutableSet;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, name = "NAME")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Player firstPlayer;

    @OneToOne(cascade = CascadeType.ALL)
    private Player secondPlayer;

    private Team() {
        // dla hibernate
    }

    Team(String name, Player firstPlayer, Player secondPlayer) {
        this.name = name;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    Long getId() {
        return id;
    }

    Set<Acceptation> createAcceptations(Match match) {
        return ImmutableSet.of(new Acceptation(firstPlayer, match), new Acceptation(secondPlayer, match));
    }
}

