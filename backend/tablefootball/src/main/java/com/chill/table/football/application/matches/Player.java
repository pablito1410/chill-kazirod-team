package com.chill.table.football.application.matches;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Team> teams = new HashSet<>();

    Player appendTeam(Team team) {
        teams.add(team);
        return this;
    }

}
