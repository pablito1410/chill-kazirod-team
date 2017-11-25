package com.chill.table.football.application.matches;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Set<Acceptation> acceptations = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Team> teams = new HashSet<>();

    Player(Long userId) {
        this.userId = userId;
    }

    Player appendTeam(Team team) {
        teams.add(team);
        return this;
    }

    Player invite(Match match) {
        acceptations.add(new Acceptation(this, match));
        return this;
    }

}
