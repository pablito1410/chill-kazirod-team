package com.chill.table.football.application.matches;

import com.chill.table.football.application.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Team> teams = new HashSet<>();

    Player(Long userId) {
        this.userId = userId;
    }

    Player appendTeam(Team team) {
        teams.add(team);
        return this;
    }

}
